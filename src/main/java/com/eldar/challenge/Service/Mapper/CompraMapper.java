package com.eldar.challenge.Service.Mapper;

import com.eldar.challenge.DTO.CompraDTO;
import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.DetalleDTO;
import com.eldar.challenge.Entities.Compra;
import com.eldar.challenge.Entities.Detalle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class CompraMapper {

    private DetalleMapper detalleMapper;

    public CompraMapper(DetalleMapper detalleMapper) {
        this.detalleMapper= detalleMapper;
    }

    public Compra map(CompraRequestDTO compraRequestDTO) throws Exception {
        Compra compra = new Compra();

        compra.setFechaCompra(LocalDate.now());

        compra.setMonto(compraRequestDTO.getMonto());

        compra.setDetalles(compraRequestDTO.getDetalles().stream().map(e->{
            Detalle detalle = this.detalleMapper.map(e);
            detalle.setCompra(compra);
            return detalle;
        }).collect(Collectors.toList()));

        return compra;
    }

    public CompraDTO map(Compra compra){
        CompraDTO compraDTO = new CompraDTO();

        compraDTO.setId(compra.getId());
        compraDTO.setMonto(compra.getMonto());
        compraDTO.setDetalles(compra.getDetalles().stream().map(e->{
            DetalleDTO detalleDTO = this.detalleMapper.map(e);
            return detalleDTO;
        }).collect(Collectors.toList()));

        return compraDTO;

    }
}
