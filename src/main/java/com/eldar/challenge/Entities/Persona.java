package com.eldar.challenge.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

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


    public Persona(String nombre, String apellido, Integer dni, LocalDate fecha_de_nacimiento, String email) throws Exception {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
        this.setEmail(email);
        this.setFecha_de_nacimiento(fecha_de_nacimiento);

    }

    public Persona() {
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {

        if(nombre == null || nombre.trim().isEmpty())
            throw new Exception("El nombre no puede estar vacio o nulo");

        if(!nombre.matches("[a-zA-Z]+"))
            throw new Exception("El nombre solo puede tener caracteres no numericos y especiales");

        this.nombre = nombre;
    }



    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws Exception {

        if(apellido == null || apellido.trim().isEmpty())
            throw new Exception("El apellido no puede estar vacio o nulo");

        if(!nombre.matches("[a-zA-Z]+"))
            throw new Exception("El apellido solo puede tener caracteres no numericos y especiales");

        this.apellido = apellido;
    }



    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) throws Exception {

        if(dni == null || dni.toString().length() < 8)
            throw new Exception("El dni no puede estar vacio o nulo, ademas debe poseer mas de 8 caracteres");

        this.dni = dni;
    }



    public LocalDate getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(LocalDate fecha_de_nacimiento) throws Exception {

        if(fecha_de_nacimiento == null)
            throw new Exception("La fecha de nacimiento no puede estar vacio o nulo");

        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if(email == null || apellido.trim().isEmpty())
            throw new Exception("El email no puede estar vacio o nulo");

        if(email.contains("@") && email.contains(".com"))
            throw new Exception("El email debe poseer el dominio y @");

        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", fecha_de_nacimiento=" + fecha_de_nacimiento +
                ", email='" + email + '\'' +
                '}';
    }
}
