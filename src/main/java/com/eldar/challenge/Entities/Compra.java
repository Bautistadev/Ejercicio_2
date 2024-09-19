package com.eldar.challenge.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaCompra")
    private LocalDate fechaCompra;

    @Column(name = "monto")
    private BigDecimal monto;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Detalle> detalles;



    public Compra(LocalDate fechaCompra,BigDecimal monto, List<Detalle> detalles) {

        this.fechaCompra = fechaCompra;
        this.detalles = detalles;
        this.monto = monto;
    }


    public Compra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", fechaCompra=" + fechaCompra +
                ", detalles=" + detalles +
                '}';
    }
}
