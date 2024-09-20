package com.eldar.challenge.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

import static com.eldar.challenge.Utils.ValidateClass.*;

@Entity
@Table(name="Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "apellido",nullable = false)
    private String apellido;
    @Column(name = "dni",nullable = false)
    private Integer dni;
    @Column(name = "birthdate",nullable = false)
    private LocalDate fecha_de_nacimiento;
    @Column(name = "email",nullable = false)
    private String email;

    public Persona( String nombre, String apellido, Integer dni, LocalDate fecha_de_nacimiento, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.email = email;
    }



    public Persona() {
    }



    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }



    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) throws Exception {
        this.dni = dni;
    }



    public LocalDate getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(LocalDate fecha_de_nacimiento){
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id) && Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido) && Objects.equals(dni, persona.dni) && Objects.equals(fecha_de_nacimiento, persona.fecha_de_nacimiento) && Objects.equals(email, persona.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, fecha_de_nacimiento, email);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id="+id+'\''+
                ",nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", fecha_de_nacimiento=" + fecha_de_nacimiento +
                ", email='" + email + '\'' +
                '}';
    }
}
