package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.PersonaAbstractDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Objects;


public class PersonaRequestDTO extends PersonaAbstractDTO {



    public PersonaRequestDTO(String nombre, String apellido, Integer dni, LocalDate fecha_de_nacimiento, String email) throws Exception {
        super(nombre,apellido,dni,fecha_de_nacimiento,email);
    }


}
