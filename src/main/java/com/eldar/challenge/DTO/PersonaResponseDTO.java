package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.PersonaAbstractDTO;

import java.util.List;
import java.util.Objects;

public class PersonaResponseDTO extends PersonaAbstractDTO {

    private Long id;

    private String message;
    private List<TarjetaDTO> tarjetas;



    public PersonaResponseDTO(Long id,String nombre, String apellido, Integer dni, String fecha_de_nacimiento, String email,String message) throws Exception {
        super(nombre, apellido, dni,fecha_de_nacimiento, email);
        this.id = id;
        this.message = message;

    }



    public PersonaResponseDTO() {
        super();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws Exception {

        if(id == null || id == 0)
            throw new Exception("El id no puede ser nulo o 0");

        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TarjetaDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
