package com.eldar.challenge.Service.Mapper;

import com.eldar.challenge.DTO.TarjetaDTO;
import com.eldar.challenge.DTO.TarjetaRequestDTO;
import com.eldar.challenge.Entities.Amex;
import com.eldar.challenge.Entities.Nara;
import com.eldar.challenge.Entities.Tarjeta;
import com.eldar.challenge.Entities.Visa;

import static com.eldar.challenge.Utils.EncriptyClass.desencriptar;
import static com.eldar.challenge.Utils.ValidateClass.localDateToString;

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



    public TarjetaDTO map(Tarjeta tarjeta) throws Exception {
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        tarjetaDTO.setId(tarjeta.getId());
        tarjetaDTO.setNombre_completo_titular(tarjeta.getNombre_completo_titular());
        tarjetaDTO.setNumero(tarjeta.getNumero());
        tarjetaDTO.setFecha_vencimiento(localDateToString (tarjeta.getFecha_vencimiento()));
        tarjetaDTO.setCVV(desencriptar(tarjeta.getCVV()));
        tarjetaDTO.setMarca(tarjeta.getMarca().getNombre());
        tarjetaDTO.setDNI(tarjeta.getCashHolder().getDni());

        return tarjetaDTO;
    }
}
