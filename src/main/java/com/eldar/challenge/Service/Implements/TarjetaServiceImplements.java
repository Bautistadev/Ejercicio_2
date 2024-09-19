package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.TarjetaRequestDTO;
import com.eldar.challenge.Entities.Persona;
import com.eldar.challenge.Entities.Tarjeta;
import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Repository.TarjetaRepository;
import com.eldar.challenge.Service.EmailService;
import com.eldar.challenge.Service.Interface.TarjetaService;
import com.eldar.challenge.Service.Mapper.TarjetaMapper;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Random;

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
    public String save(TarjetaRequestDTO tarjetaRequestDTO) throws Exception {

        //MAPEAMOS LOS DATOS DEL DTO TARJETA AL TARJETA ENTIDAD
        Tarjeta tarjetaSave = this.tarjetaMapper.map(tarjetaRequestDTO);


        //VALIDAMOS EXISTENCIA DE LA PERSONA
        if(!this.personaRepository.existsByDni(tarjetaRequestDTO.getDNI()))
            return "ERROR: Persona no existente";
        Persona persona = this.personaRepository.findByDni(tarjetaRequestDTO.getDNI()).get();


        //VALIDAMOS EXISTENCIA DE TARJETA REPETIDA
         if(tarjetaRepository.findByCardHolderId(persona.getId()).stream().anyMatch(e->e.getMarca().equals(tarjetaSave.getMarca())))
            return "ERROR: YA EXISTE UNA TARJETA CON ESTA MARCA, PERTENECIENTE A LA PERSONA";


        //COMPLETAMOS LOS DATOS FALTANTES DE LA TARJETA
        //LA BASE DE DATOS ESTA CONDIFGURADA PARA QUE EL PAM Y EL CVV SEAN UNICOS
        Long pam = generarNumeroDe16Cifras();
        Integer cvv = generarNumeroDeCVV();

        tarjetaSave.setNumero(pam);
        tarjetaSave.setCVV(encriptar(cvv.toString()));
        tarjetaSave.setFecha_vencimiento(crearFechaVencimiento(LocalDate.now(),48));
        tarjetaSave.setCashHolder(persona);


        //PERSISTIMOS
        try {
            this.tarjetaRepository.save(tarjetaSave);
        }catch (Exception e){
            return "ERROR: CVV o PAM REPETIDOS";
        }

        //ENVIAMOS MAIL
        this.emailService.sendInfoTarjetaMessage(tarjetaRequestDTO.getNombre_completo_titular(),formatearNumeroTarjeta(pam.toString()),cvv.toString(),tarjetaRequestDTO.getMarca(),persona.getEmail());

        //MOSTRAMOS MEN
        return "Tu operación ha sido procesada exitosamente. ¡Gracias por confiar en nosotros!. Los datos de tu tarjeta se verán reflejados en tu correo electrónico";
    }


}
