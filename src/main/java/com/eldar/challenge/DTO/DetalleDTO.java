package com.eldar.challenge.DTO;

import jakarta.persistence.Column;

public class DetalleDTO {

    private String producto;

    private Integer cantidad;

    public DetalleDTO(String producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public DetalleDTO() {
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
