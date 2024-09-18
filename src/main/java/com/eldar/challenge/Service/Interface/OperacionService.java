package com.eldar.challenge.Service.Interface;

import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.OperacionRequestDTO;
import com.eldar.challenge.DTO.OperacionResponseDTO;

public interface OperacionService {

    public String compra(CompraRequestDTO compraRequestDTO) throws Exception;

    public OperacionResponseDTO findOperacion(Long nroOperacion);

}
