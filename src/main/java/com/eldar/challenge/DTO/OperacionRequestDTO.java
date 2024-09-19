package com.eldar.challenge.DTO;

public class OperacionRequestDTO {
    private Long nroOperacion;

    public OperacionRequestDTO(Long nroOperacion) {
        this.nroOperacion = nroOperacion;
    }

    public Long getNroOperacion() {
        return nroOperacion;
    }

    public void setNroOperacion(Long nroOperacion) {
        this.nroOperacion = nroOperacion;
    }
}
