package com.eldar.challenge.DTO.Abstract;

import java.time.LocalDate;
import java.util.Objects;

public abstract class PersonaAbstractDTO {

    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fecha_de_nacimiento;
    private String email;

    public PersonaAbstractDTO(String nombre, String apellido, Integer dni, LocalDate fecha_de_nacimiento, String email) {
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

        if(!apellido.matches("[a-zA-Z]+"))
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

        if(email == null || email.trim().isEmpty())
            throw new Exception("El email no puede estar vacio o nulo");

        if(!email.contains("@") || !email.contains(".com"))
            throw new Exception("El email debe poseer el dominio y @");

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
