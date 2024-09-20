package com.eldar.challenge.DTO.Abstract;

public abstract class MarcaDTO {

    private Long id;
    private String nombre;

    public MarcaDTO(Long id,String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
