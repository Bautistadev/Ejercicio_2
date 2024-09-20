package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Service.Interface.PersonaService;
import com.eldar.challenge.Service.Mapper.PersonaMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImplements implements PersonaService {

    private PersonaMapper personaMapper;
    private PersonaRepository personaRepository;



    /*
    * INYECTAMOS DEPENDENCIAS
    * */
    public PersonaServiceImplements(PersonaMapper personaMapper,PersonaRepository personaRepository) {
        this.personaMapper = personaMapper;
        this.personaRepository = personaRepository;
    }



    /**
     * @function: Guardar persona
     * @param: PersonaRequestDTO
     * @return PersonaResponseDTO
     * */
    @Override
    public PersonaResponseDTO save(PersonaRequestDTO personaRequestDTO) throws Exception {

        PersonaResponseDTO response = new PersonaResponseDTO();

        Persona personaSave = this.personaRepository.save(this.personaMapper.map(personaRequestDTO));
        response = this.personaMapper.map(personaSave);
        response.setMessage("INFO: PERSONA PERSISTIDA CON EXITO");


        return response;
    }
    @Override
    public Boolean existsByDni(Integer dni){
        return personaRepository.existsByDni(dni);
    }

}
