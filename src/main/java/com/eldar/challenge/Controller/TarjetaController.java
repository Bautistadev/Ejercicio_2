package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.TarjetaRequestDTO;
import com.eldar.challenge.Service.Interface.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TarjetaController {

    private TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping("/altaTarjeta")
    public ResponseEntity<String> altaTarjeta(@Valid @RequestBody TarjetaRequestDTO tarjetaRequestDTO) throws Exception {
        String response = this.tarjetaService.save(tarjetaRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
