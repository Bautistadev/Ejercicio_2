package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.CompraDTO;
import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.OperacionResponseDTO;
import com.eldar.challenge.DTO.ResponseEntityDTO;
import com.eldar.challenge.Service.Interface.OperacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import static com.eldar.challenge.Utils.ValidateClass.isExpired;
import static com.eldar.challenge.Utils.ValidateClass.localDateToString;

@RestController
@RequestMapping("/api/v1/operacion")
public class OperacionController {

    private OperacionService operacionService;

    public OperacionController(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @PostMapping("/compra")
    public ResponseEntity<ResponseEntityDTO<CompraDTO>>compra(@Valid @RequestBody CompraRequestDTO compraRequestDTO) throws Exception {

        //VALIDACIONES DEL MONTO
        if(compraRequestDTO.getMonto().compareTo(new BigDecimal(10000)) > 0)
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: el monto no puede superar los $10000"),HttpStatus.BAD_REQUEST);

        if(compraRequestDTO.getMonto().compareTo(new BigDecimal(0)) <0)
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: el monto no puede ser menor que 0"),HttpStatus.BAD_REQUEST);

        if(compraRequestDTO.getMonto().compareTo(new BigDecimal(0)) == 0)
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: el monto no puede ser 0"),HttpStatus.BAD_REQUEST);

        if(compraRequestDTO.getDetalles().isEmpty())
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: debes ingresar el detalle"),HttpStatus.BAD_REQUEST);

        if(this.operacionService.findTarjetaBypan(compraRequestDTO.getpan()) == null)
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: tarjeta no registrada"),HttpStatus.NOT_FOUND);

        if(isExpired(localDateToString(this.operacionService.findTarjetaBypan(compraRequestDTO.getpan()).getFecha_vencimiento())))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: tarjeta caducada"),HttpStatus.BAD_REQUEST);

        if(!this.operacionService.validateCVV(compraRequestDTO.getpan(),compraRequestDTO.getCvv()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: cvv fallido"),HttpStatus.NOT_FOUND);


        CompraDTO compraDTO = this.operacionService.compra(compraRequestDTO);

       return new ResponseEntity<>(ResponseEntityDTO.success("Info: Compra registrada con exito",compraDTO),HttpStatus.OK);
    }

   @GetMapping("/tasa/{marca}/{importe}")
    public ResponseEntity<ResponseEntityDTO<List<OperacionResponseDTO>>>operacion(@PathVariable String marca, BigDecimal importe){

        if(marca == null || importe == null || importe.equals(0))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: Mal ingreso de los datos"),HttpStatus.BAD_REQUEST);
        
        if(!operacionService.existsMarca(marca)){
            return new ResponseEntity<>(ResponseEntityDTO.error("Error: La marca no existente"),HttpStatus.NOT_FOUND);
        }

        List<OperacionResponseDTO> operacionResponseDTOList = this.operacionService.findOperacion(importe,marca);
        return new ResponseEntity<>(ResponseEntityDTO.success("Busqueda correcta",operacionResponseDTOList),HttpStatus.OK);
    }
}
