package com.eldar.challenge.DTO;

import java.math.BigDecimal;

public class OperacionResponseDTO {

    private BigDecimal tasa;
    private BigDecimal importe;
    private String marca;

    public OperacionResponseDTO(BigDecimal tasa, BigDecimal importe, String marca) {
        this.tasa = tasa;
        this.importe = importe;
        this.marca = marca;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
