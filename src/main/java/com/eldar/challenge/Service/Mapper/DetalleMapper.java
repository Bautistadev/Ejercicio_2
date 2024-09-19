package com.eldar.challenge.Service.Mapper;

import com.eldar.challenge.DTO.DetalleDTO;
import com.eldar.challenge.Entities.Detalle;

public class DetalleMapper {
    public DetalleMapper() {

    }

    public Detalle map(DetalleDTO detalleDTO){
        Detalle detalle = new Detalle();
        detalle.setCantidad(detalleDTO.getCantidad());
        detalle.setProducto(detalleDTO.getProducto());

        return detalle;
    }
}
