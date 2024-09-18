package com.eldar.challenge.Service.Interface;

import com.eldar.challenge.DTO.PersonaDTO;
import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.Entities.Persona;
import org.springframework.http.ResponseEntity;

public interface PersonaService {
    public PersonaResponseDTO save(PersonaRequestDTO personaRequestDTO) throws Exception;
    public PersonaResponseDTO update(PersonaDTO personaDTO);
    public ResponseEntity<String> remove(Integer dni);
}
