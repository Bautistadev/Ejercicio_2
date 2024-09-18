package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.PersonaDTO;
import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.DTO.TarjetaDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Repository.TarjetaRepository;
import com.eldar.challenge.Service.Interface.PersonaService;
import com.eldar.challenge.Service.Mapper.PersonaMapper;
import com.eldar.challenge.Service.Mapper.TarjetaMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImplements implements PersonaService {

    private PersonaMapper personaMapper;
    private PersonaRepository personaRepository;




    public PersonaServiceImplements(PersonaMapper personaMapper,PersonaRepository personaRepository) {
        this.personaMapper = personaMapper;
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaResponseDTO save(PersonaRequestDTO personaRequestDTO) throws Exception {

        Persona personaSave = this.personaRepository.save(this.personaMapper.map(personaRequestDTO));
        PersonaResponseDTO response = this.personaMapper.map(personaSave);


        return response;
    }

    @Override
    public PersonaResponseDTO update(PersonaDTO personaDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> remove(Integer dni) {
        return null;
    }
}
