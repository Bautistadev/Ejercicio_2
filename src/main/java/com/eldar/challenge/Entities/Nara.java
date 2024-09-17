package com.eldar.challenge.Entities;

import com.eldar.challenge.Entities.Abstract.Marca;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Nara extends Marca {

    private static Float PORCENTAJE = 0.5F;

    private static String NOMBRE = "NARA";

    public Nara() {
        super(NOMBRE);
    }

    @Override
    public BigDecimal calcularTasa(LocalDate fecha) {
        BigDecimal dia = new BigDecimal(fecha.getDayOfMonth());
        BigDecimal porcentaje = new BigDecimal(PORCENTAJE);

        //Garantizamos una division con una presicion de 34 digitos y redondeamos a 2 cifras decimales
        return dia.multiply(porcentaje, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP);
    }
}
