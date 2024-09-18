package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.Service.Interface.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {

    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }


    @PostMapping("/altaPersona")
    public ResponseEntity<PersonaResponseDTO>addPersona(@Valid @RequestBody PersonaRequestDTO personaRequestDTO) throws Exception {
        PersonaResponseDTO response =  this.personaService.save(personaRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
