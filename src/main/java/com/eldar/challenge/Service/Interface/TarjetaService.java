package com.eldar.challenge.Service.Interface;

import com.eldar.challenge.DTO.TarjetaDTO;
import com.eldar.challenge.DTO.TarjetaRequestDTO;

public interface TarjetaService {

    public TarjetaDTO save(TarjetaRequestDTO tarjetaRequestDTO) throws Exception;

    Boolean existsByDni(Integer dni);

    Boolean existsTarjeta(Integer dni,String marca);

    Boolean existsMarca(String marca);

}
