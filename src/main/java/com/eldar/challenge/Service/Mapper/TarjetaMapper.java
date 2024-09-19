package com.eldar.challenge.Service.Mapper;

import com.eldar.challenge.DTO.TarjetaDTO;
import com.eldar.challenge.DTO.TarjetaRequestDTO;
import com.eldar.challenge.Entities.Amex;
import com.eldar.challenge.Entities.Nara;
import com.eldar.challenge.Entities.Tarjeta;
import com.eldar.challenge.Entities.Visa;

public class TarjetaMapper {

    public TarjetaMapper() {
    }

    public Tarjeta map(TarjetaRequestDTO tarjetaRequestDTO) throws Exception {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombre_completo_titular(tarjetaRequestDTO.getNombre_completo_titular());

        switch (tarjetaRequestDTO.getMarca().toUpperCase()){
            case "VISA": tarjeta.setMarca(new Visa()); break;
            case "AMEX": tarjeta.setMarca(new Amex()); break;
            case "NARA": tarjeta.setMarca(new Nara()); break;
            default: throw new Exception("Marca no registrada");
        }

        return tarjeta;

    }

    public TarjetaDTO map(Tarjeta tarjeta){
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        tarjetaDTO.setId(tarjeta.getId());
        tarjetaDTO.setNombre_completo_titular(tarjeta.getNombre_completo_titular());
        tarjetaDTO.setNumero(tarjeta.getNumero());
        tarjetaDTO.setFecha_vencimiento(tarjeta.getFecha_vencimiento());
        tarjetaDTO.setCVV(tarjeta.getCVV());

        return tarjetaDTO;
    }
}
