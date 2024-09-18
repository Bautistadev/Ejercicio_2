package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.PersonaAbstractDTO;
import com.eldar.challenge.Entities.Tarjeta;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PersonaResponseDTO extends PersonaAbstractDTO {

    private Long id;


    private List<TarjetaDTO> tarjetas;

    public PersonaResponseDTO(Long id,String nombre, String apellido, Integer dni, LocalDate fecha_de_nacimiento, String email) throws Exception {
        super(nombre, apellido, dni, fecha_de_nacimiento, email);
        this.id = id;
        this.tarjetas = null;

    }

    public PersonaResponseDTO(String nombre, String apellido, Integer dni, LocalDate fecha_de_nacimiento, String email, Long id, List<TarjetaDTO> tarjetas) {
        super(nombre, apellido, dni, fecha_de_nacimiento, email);
        this.id = id;
        this.tarjetas = tarjetas;
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
