package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.OperacionRequestDTO;
import com.eldar.challenge.DTO.OperacionResponseDTO;
import com.eldar.challenge.Service.Interface.OperacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class OperacionController {

    private OperacionService operacionService;

    public OperacionController(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @PostMapping("/compra")
    public ResponseEntity<String>compra(@Valid @RequestBody CompraRequestDTO compraRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.operacionService.compra(compraRequestDTO));
    }

    @GetMapping("/operation/{nroOperacion}")
    public ResponseEntity<OperacionResponseDTO>operacion(@PathVariable Long nroOperacion){
        return ResponseEntity.status(HttpStatus.OK).body(operacionService.findOperacion(nroOperacion));
    }
}
