package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.PersonaAbstractDTO;

import java.time.LocalDate;

public class PersonaDTO extends PersonaAbstractDTO {

    private Long id;

    public PersonaDTO(Long id,String nombre, String apellido, Integer dni, String fecha_de_nacimiento, String email) {
        super(nombre, apellido, dni, fecha_de_nacimiento, email);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws Exception {
        if(id == null || id == 0)
            throw new Exception("El id no puede ser nulo o 0");

        this.id = id;
    }


}
