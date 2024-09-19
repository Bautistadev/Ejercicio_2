package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Service.Interface.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {

    private PersonaService personaService;


    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }


    @PostMapping("/altaPersona")
    public ResponseEntity<PersonaResponseDTO>addPersona(@Valid @RequestBody PersonaRequestDTO personaRequestDTO)  {
        PersonaResponseDTO response = null;
        try {
            response = this.personaService.save(personaRequestDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
