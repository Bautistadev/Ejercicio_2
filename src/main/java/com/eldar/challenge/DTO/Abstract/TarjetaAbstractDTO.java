package com.eldar.challenge.DTO.Abstract;

import com.eldar.challenge.Entities.Abstract.Marca;

public abstract class TarjetaAbstractDTO {
    private String nombre_completo_titular;
    private Integer DNI;
    private String marca;

    public TarjetaAbstractDTO(String nombre_completo_titular, Integer DNI, String marca) {
        this.nombre_completo_titular = nombre_completo_titular;
        this.DNI = DNI;
        this.marca = marca;
    }


    public TarjetaAbstractDTO() {
    }

    public String getNombre_completo_titular() {
        return nombre_completo_titular;
    }

    public void setNombre_completo_titular(String nombre_completo_titular) {
        this.nombre_completo_titular = nombre_completo_titular;
    }

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
