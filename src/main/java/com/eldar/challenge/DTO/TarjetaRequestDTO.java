package com.eldar.challenge.DTO;

import com.eldar.challenge.DTO.Abstract.TarjetaAbstractDTO;

public class TarjetaRequestDTO extends TarjetaAbstractDTO {
    public TarjetaRequestDTO(String nombre_completo_titular, Integer DNI, String marca) {
        super(nombre_completo_titular, DNI, marca);
    }
}
