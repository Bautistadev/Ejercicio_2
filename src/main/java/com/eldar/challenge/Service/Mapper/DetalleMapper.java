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

    public DetalleDTO map(Detalle detalle){
        DetalleDTO detalleDTO = new DetalleDTO();
        detalleDTO.setProducto(detalle.getProducto());
        detalleDTO.setCantidad(detalle.getCantidad());
        return detalleDTO;
    }
}
