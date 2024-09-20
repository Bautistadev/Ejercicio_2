package com.eldar.challenge.DTO;

import com.eldar.challenge.Entities.Detalle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CompraRequestDTO {



    private BigDecimal monto;

    private Long pan;

    private String cvv;

    private List<DetalleDTO> detalles;

    public CompraRequestDTO(BigDecimal monto, Long pan, String cvv, List<DetalleDTO> detalles) {
        this.monto = monto;
        this.pan = pan;
        this.cvv = cvv;
        this.detalles = detalles;
    }

    public CompraRequestDTO() {
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

    public Long getpan() {
        return pan;
    }

    public void setpan(Long pan) {
        this.pan = pan;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
