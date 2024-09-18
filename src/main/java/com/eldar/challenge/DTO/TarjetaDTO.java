package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.TarjetaAbstractDTO;

import java.time.LocalDate;

public class TarjetaDTO extends TarjetaAbstractDTO {

    private Long id;
    private Long numero;
    private LocalDate fecha_vencimiento;
    private String CVV;

    public TarjetaDTO(String nombre_completo_titular, Integer DNI, String marca, Long id, Long numero, LocalDate fecha_vencimiento, String CVV) {
        super(nombre_completo_titular, DNI, marca);
        this.id = id;
        this.numero = numero;
        this.fecha_vencimiento = fecha_vencimiento;
        this.CVV = CVV;
    }

    public TarjetaDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
