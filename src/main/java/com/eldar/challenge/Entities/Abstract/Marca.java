package com.eldar.challenge.Entities.Abstract;

import com.eldar.challenge.Entities.Interfaces.Tasa;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@ToString
@Entity
@Table(name="Marca")
public abstract class Marca implements Tasa {

    @Id
    private Long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    public Marca(Long id,String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Objects.equals(id, marca.id) && Objects.equals(nombre, marca.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
