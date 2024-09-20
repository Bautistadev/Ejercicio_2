package com.eldar.challenge.DTO;

import java.math.BigDecimal;
import java.util.List;

public class CompraDTO {

    private Long id;

    private BigDecimal monto;

    private String titular;
    private List<DetalleDTO> detalles;



    public CompraDTO(Long id, BigDecimal monto,String titular, List<DetalleDTO> detalles) {
        this.id = id;
        this.monto = monto;
        this.detalles = detalles;
        this.titular = titular;
    }

    public CompraDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public List<DetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDTO> detalles) {
        this.detalles = detalles;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
