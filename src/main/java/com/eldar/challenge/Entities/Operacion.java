package com.eldar.challenge.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "operacion")
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha")
    private LocalDateTime fechaOperacion;

    @ManyToOne
    @JoinColumn(name = "tarjetaId")
    private Tarjeta tarjeta;

    @ManyToOne
    @JoinColumn(name = "CompraId")
    private Compra compra;


    public Operacion(LocalDateTime fechaOperacion, Tarjeta tarjeta, Compra compra) {
        this.fechaOperacion = fechaOperacion;
        this.tarjeta = tarjeta;
        this.compra = compra;
    }

    public Operacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
