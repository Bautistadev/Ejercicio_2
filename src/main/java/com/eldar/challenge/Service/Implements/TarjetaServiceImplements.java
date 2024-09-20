package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.TarjetaDTO;
import com.eldar.challenge.DTO.TarjetaRequestDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Entities.Tarjeta;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Repository.TarjetaRepository;
import com.eldar.challenge.Service.EmailService;
import com.eldar.challenge.Service.Interface.TarjetaService;
import com.eldar.challenge.Service.Mapper.TarjetaMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.eldar.challenge.Utils.EncriptyClass.encriptar;
import static com.eldar.challenge.Utils.ValidateClass.*;

@Service
public class TarjetaServiceImplements implements TarjetaService {


    private TarjetaMapper tarjetaMapper;
    private TarjetaRepository tarjetaRepository;
    private PersonaRepository personaRepository;
    private EmailService emailService;


    //INYECTAMOS DEPENDENCIAS
    public TarjetaServiceImplements(TarjetaMapper tarjetaMapper,TarjetaRepository tarjetaRepository,PersonaRepository personaRepository,EmailService emailService) {
        this.tarjetaMapper = tarjetaMapper;
        this.tarjetaRepository = tarjetaRepository;
        this.personaRepository = personaRepository;
        this.emailService = emailService;
    }


    /**
     * @function: Alta y persistir tarjeta
     * @param: Datos de la tarjeta
     * @resturn: Mensaje de guardado exitoso
     * */
    @Override
    public TarjetaDTO save(TarjetaRequestDTO tarjetaRequestDTO) throws Exception {

        //MAPEAMOS LOS DATOS DEL DTO TARJETA AL TARJETA ENTIDAD
        Tarjeta tarjetaSave = this.tarjetaMapper.map(tarjetaRequestDTO);

        Persona persona = this.personaRepository.findByDni(tarjetaRequestDTO.getDNI()).get();


        //COMPLETAMOS LOS DATOS FALTANTES DE LA TARJETA
        //LA BASE DE DATOS ESTA CONDIFGURADA PARA QUE EL pan Y EL CVV SEAN UNICOS
        Long pan = generarNumeroDe16Cifras();
        Integer cvv = generarNumeroDeCVV();

        tarjetaSave.setNumero(pan);
        tarjetaSave.setCVV(encriptar(cvv.toString()));
        tarjetaSave.setFecha_vencimiento(crearFechaVencimiento(LocalDate.now(),48));
        tarjetaSave.setCashHolder(persona);


        //PERSISTIMOS
        TarjetaDTO tarjetaDTO = this.tarjetaMapper.map(this.tarjetaRepository.save(tarjetaSave));

        //ENVIAMOS MAIL
        this.emailService.sendInfoTarjetaMessage(tarjetaRequestDTO.getNombre_completo_titular(),formatearNumeroTarjeta(pan.toString()),cvv.toString(),tarjetaRequestDTO.getMarca(),persona.getEmail());

        //MOSTRAMOS MEN
        return tarjetaDTO;
    }

    @Override
    public Boolean existsByDni(Integer dni){
        return this.personaRepository.existsByDni(dni);
    }

    @Override
    public Boolean existsTarjeta(Integer dni, String marca){
        Persona persona = this.personaRepository.findByDni(dni).get();
        return tarjetaRepository.findByCardHolderId(persona.getId()).stream().anyMatch(e->e.getMarca().getNombre().equalsIgnoreCase(marca));
    }

    @Override
    public Boolean existsMarca(String marca){
        switch (marca.toUpperCase()){
            case "VISA": return true;
            case "AMEX": return true;
            case "NARA": return true;
            default: return false;
        }
    }


}
