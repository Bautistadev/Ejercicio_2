package com.eldar.challenge.Service.Interface;

import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PersonaService {
    public PersonaResponseDTO save(PersonaRequestDTO personaRequestDTO) throws Exception;

    public Boolean existsByDni(Integer dni);
}
