package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.ResponseEntityDTO;
import com.eldar.challenge.DTO.TarjetaDTO;
import com.eldar.challenge.DTO.TarjetaRequestDTO;
import com.eldar.challenge.Service.Interface.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eldar.challenge.Utils.ValidateClass.nombreTitularValida;

@RestController
@RequestMapping("/api/v1/tarjeta")
public class TarjetaController {

    private TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping("/altaTarjeta")
    public ResponseEntity<ResponseEntityDTO<TarjetaDTO>> altaTarjeta(@Valid @RequestBody TarjetaRequestDTO tarjetaRequestDTO) throws Exception {

        if(!nombreTitularValida(tarjetaRequestDTO.getNombre_completo_titular()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error, el nombre del titular debe ser completo y no puede tener caracteres especiales"),HttpStatus.BAD_REQUEST);

        //VALIDAMO EXISTENCIA DE LA PERSONA
        if(!this.tarjetaService.existsByDni(tarjetaRequestDTO.getDNI()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error persona no existente"),HttpStatus.NOT_FOUND);

        //VALIDAMOS QUE LA TARJETA EXISTA
        if(this.tarjetaService.existsTarjeta(tarjetaRequestDTO.getDNI(),tarjetaRequestDTO.getMarca()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error tarjeta registrada"),HttpStatus.CONFLICT);

        //VALIDAMO LA MARCA
        if(!this.tarjetaService.existsMarca(tarjetaRequestDTO.getMarca()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error marca no registrada"),HttpStatus.NOT_FOUND);


        try {
           TarjetaDTO response = this.tarjetaService.save(tarjetaRequestDTO);
           return new ResponseEntity<>(ResponseEntityDTO.success("Tarjeta persistida",response),HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(ResponseEntityDTO.error("Error en la persistencia"),HttpStatus.NOT_FOUND);
       }
    }
}
