package com.eldar.challenge.Controller;

import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.DTO.ResponseEntityDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Service.Interface.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.eldar.challenge.Utils.ValidateClass.*;
import static com.eldar.challenge.Utils.ValidateClass.toLocalDate;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/altaPersona")
    public ResponseEntity<ResponseEntityDTO<PersonaResponseDTO>>addPersona(@Valid @RequestBody PersonaRequestDTO personaRequestDTO)  {
        PersonaResponseDTO response = null;

        //VALIDAMOS EXISTENCIA DE LA PERSONA EN LA BASE DE DATOS
        if(this.personaService.existsByDni(personaRequestDTO.getDni()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error, Persona ya existente"),HttpStatus.CONFLICT);

        //VALIDAMOS EMAIL DE LA PERSONA
        if(!validateEmail(personaRequestDTO.getEmail()))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error en el ingreso del email"),HttpStatus.BAD_REQUEST);

        //VALIDAMOS FORMATO Y FECHA DE NACIMIENTO
        if(!validateFormatDate(personaRequestDTO.getFecha_de_nacimiento()) || !validBirthDate(toLocalDate(personaRequestDTO.getFecha_de_nacimiento())))
            return new ResponseEntity<>(ResponseEntityDTO.error("Error en el formato de fecha"),HttpStatus.BAD_REQUEST);

        try {
            response = this.personaService.save(personaRequestDTO);
        } catch (Exception e) {
            //CUALQUIER OTRO ERROR
            return new ResponseEntity<>(ResponseEntityDTO.error("Error en la persistencia"),HttpStatus.NOT_FOUND);
        }
        //RETORNAMOS A LA PERSONA
        return new ResponseEntity<>(ResponseEntityDTO.success("Persona Persistida",response),HttpStatus.OK);
    }


}
