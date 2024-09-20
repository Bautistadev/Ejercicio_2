package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.MarcaDTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

public class AmexDTO extends MarcaDTO {
    private static Float PORCENTAJE = 0.1F;
    private static String NOMBRE = "AMEX";

    private static Long ID = 3L;

    public AmexDTO(){
        super(ID,NOMBRE);

    }

    public BigDecimal calcularTasa(LocalDate fecha) {
        BigDecimal mes = new BigDecimal(fecha.getMonthValue());
        BigDecimal porcentaje = new BigDecimal(PORCENTAJE);

        //Garantizamos una division con una presicion de 34 digitos y redondeamos a 2 cifras decimales
        return mes.multiply(porcentaje, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP);
    }
}
