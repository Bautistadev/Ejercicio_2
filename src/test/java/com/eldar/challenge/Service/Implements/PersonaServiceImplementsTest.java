package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.Abstract.PersonaAbstractDTO;
import com.eldar.challenge.DTO.PersonaRequestDTO;
import com.eldar.challenge.DTO.PersonaResponseDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Service.Interface.PersonaService;
import com.eldar.challenge.Service.Mapper.PersonaMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.eldar.challenge.Utils.ValidateClass.localDateToString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonaServiceImplementsTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private PersonaMapper personaMapper;

    @InjectMocks
    private PersonaServiceImplements personaService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() throws Exception {

        Persona personaMock = new Persona();
        personaMock.setNombre("test");
        personaMock.setApellido("test");
        personaMock.setDni(123456789);
        personaMock.setEmail("test@gmail.com");
        personaMock.setFecha_de_nacimiento(LocalDate.of(2000,02,02));

        PersonaRequestDTO personaSimulate = new PersonaRequestDTO();
        personaSimulate.setNombre("test");
        personaSimulate.setApellido("test");
        personaSimulate.setDni(123456789);
        personaSimulate.setEmail("test@gmail.com");
        personaSimulate.setFecha_de_nacimiento(localDateToString(LocalDate.of(2000,02,02)));


        //CONFIGURAMOS SIMULACION/COMPORTAMIENTO
        when(personaMapper.map(Mockito.any(PersonaRequestDTO.class))).thenReturn(personaMock);
        when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(personaMock);
        when(personaMapper.map(Mockito.any(Persona.class))).thenReturn(new PersonaResponseDTO());




        PersonaResponseDTO result = personaService.save(personaSimulate);

        Assertions.assertThat(result).isNotNull();


    }
}