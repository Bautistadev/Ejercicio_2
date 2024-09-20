package com.eldar.challenge.Service.Interface;

import com.eldar.challenge.DTO.CompraDTO;
import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.OperacionResponseDTO;
import com.eldar.challenge.Entities.Tarjeta;

import java.math.BigDecimal;
import java.util.List;

import static com.eldar.challenge.Utils.EncriptyClass.desencriptar;

public interface OperacionService {

    public CompraDTO compra(CompraRequestDTO compraRequestDTO) throws Exception;

    List<OperacionResponseDTO> findOperacion(BigDecimal monto, String marca);

    public Tarjeta findTarjetaBypan(Long pan);

    public Boolean validateCVV(Long pan, String CVV) throws Exception;
    public Boolean existsMarca(String marca);
}
