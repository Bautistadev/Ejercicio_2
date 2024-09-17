package com.eldar.challenge.Entities.Interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Tasa {
    public BigDecimal calcularTasa(LocalDate fecha);
}
