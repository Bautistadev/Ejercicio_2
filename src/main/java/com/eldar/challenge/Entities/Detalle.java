package com.eldar.challenge.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="detalle")
public class Detalle {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto",nullable = false)
    private String producto;

    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="compraId")
    @JsonIgnore
    private Compra compra;


    public Detalle(String producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Detalle() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
