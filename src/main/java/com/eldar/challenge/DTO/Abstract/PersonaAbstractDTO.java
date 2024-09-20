package com.eldar.challenge.DTO.Abstract;

import java.time.LocalDate;
import java.util.Objects;

import static com.eldar.challenge.Utils.ValidateClass.*;

public abstract class PersonaAbstractDTO {

    private String nombre;
    private String apellido;
    private Integer dni;
    private String fecha_de_nacimiento;
    private String email;

    public PersonaAbstractDTO(String nombre, String apellido, Integer dni, String fecha_de_nacimiento, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.email = email;
    }

    public PersonaAbstractDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {

        if(!validateText(nombre))
            throw new Exception("El nombre no puede estar vacio o nulo");

        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws Exception {
        if(!validateText(apellido))
            throw new Exception("El apellido no puede estar vacio o nulo");

        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) throws Exception {
        this.dni = dni;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) throws Exception {

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
        PersonaAbstractDTO that = (PersonaAbstractDTO) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(dni, that.dni) && Objects.equals(fecha_de_nacimiento, that.fecha_de_nacimiento) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, dni, fecha_de_nacimiento, email);
    }
}
