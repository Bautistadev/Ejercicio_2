package com.eldar.challenge.Service.Mapper;

import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.Entities.Persona;

import java.util.ArrayList;

import static com.eldar.challenge.Utils.ValidateClass.localDateToString;
import static com.eldar.challenge.Utils.ValidateClass.toLocalDate;

public class PersonaMapper {

    public Persona map(PersonaRequestDTO personaRequestDTO) throws Exception {
        Persona persona = new Persona();

        persona.setNombre(personaRequestDTO.getNombre());
        persona.setApellido(personaRequestDTO.getApellido());
        persona.setDni(personaRequestDTO.getDni());
        persona.setEmail(personaRequestDTO.getEmail());
        persona.setFecha_de_nacimiento(toLocalDate(personaRequestDTO.getFecha_de_nacimiento()));

        return persona;
    }

    public PersonaResponseDTO map(Persona persona) throws Exception{
        PersonaResponseDTO personaResponseDTO = new PersonaResponseDTO();
        personaResponseDTO.setId(persona.getId());
        personaResponseDTO.setNombre(persona.getNombre());
        personaResponseDTO.setApellido(persona.getApellido());
        personaResponseDTO.setDni(persona.getDni());
        personaResponseDTO.setEmail(persona.getEmail());
        personaResponseDTO.setFecha_de_nacimiento(localDateToString(persona.getFecha_de_nacimiento()));
        personaResponseDTO.setTarjetas(new ArrayList<>());
        return personaResponseDTO;
    }


}
