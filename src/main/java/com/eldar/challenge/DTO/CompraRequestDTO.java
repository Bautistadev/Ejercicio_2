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

    private Long pam;

    private String cvv;

    private List<DetalleDTO> detalles;

    public CompraRequestDTO(BigDecimal monto, Long pam, String cvv, List<DetalleDTO> detalles) {
        this.monto = monto;
        this.pam = pam;
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

    public Long getPam() {
        return pam;
    }

    public void setPam(Long pam) {
        this.pam = pam;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
