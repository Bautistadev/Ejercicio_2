package com.eldar.challenge.Entities.Abstract;

import com.eldar.challenge.Entities.Interfaces.Tasa;
import jakarta.persistence.*;
import lombok.*;



@ToString
@AllArgsConstructor
@Entity
@Table(name="Marca")
public abstract class Marca implements Tasa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    public Marca(String nombre) {
        this.nombre = nombre;
    }
}
