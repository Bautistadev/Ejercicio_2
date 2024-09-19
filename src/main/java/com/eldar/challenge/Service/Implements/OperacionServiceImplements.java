package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.OperacionResponseDTO;
import com.eldar.challenge.Entities.*;
import com.eldar.challenge.Repository.CompraRepository;
import com.eldar.challenge.Repository.DetalleRepository;
import com.eldar.challenge.Repository.OperacionRespository;
import com.eldar.challenge.Repository.TarjetaRepository;
import com.eldar.challenge.Service.EmailService;
import com.eldar.challenge.Service.Interface.OperacionService;
import com.eldar.challenge.Service.Mapper.CompraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.eldar.challenge.Utils.EncriptyClass.encriptar;


@Service
public class OperacionServiceImplements implements OperacionService {

    private CompraMapper compraMapper;
    private CompraRepository compraRepository;
    private DetalleRepository detalleRepository;

    private TarjetaRepository tarjetaRepository;

    private OperacionRespository operacionRespository;

    @Autowired
    private EmailService emailService;

    public OperacionServiceImplements(CompraMapper compraMapper, CompraRepository compraRepository, DetalleRepository detalleRepository,TarjetaRepository tarjetaRepository,OperacionRespository operacionRespository) {
        this.compraMapper = compraMapper;
        this.compraRepository = compraRepository;
        this.detalleRepository = detalleRepository;
        this.tarjetaRepository = tarjetaRepository;
        this.operacionRespository= operacionRespository;
    }

    @Override
    public String compra(CompraRequestDTO compraRequestDTO) throws Exception {

        if(compraRequestDTO.getMonto().compareTo(new BigDecimal(10000)) > 0)
            return "ERROR: El monto supera los 10000$";

        if(compraRequestDTO.getMonto().compareTo(new BigDecimal(0)) <0)
            return "ERROR: El monto no puede ser menor a 0";

        if(compraRequestDTO.getMonto().compareTo(new BigDecimal(0)) == 0)
            return "ERROR: El monto no puede ser  0";


        if(compraRequestDTO.getDetalles().isEmpty())
            return "ERROR: Una compra no puede existir sin un detalle";



        Tarjeta tarjeta = null;
        try {
            tarjeta = this.tarjetaRepository.findByNumero(compraRequestDTO.getPam()).get();
            if(!encriptar(compraRequestDTO.getCvv()).equals(tarjeta.getCVV()))
                return "ERROR: CVV INCORRECTO";
        }catch (Exception e){
            return "ERROR: PAM INCORRECTO";
        }


        //GUARDAMOS LA COMPRA
        Compra compraDB = null;
        try {
            compraDB = this.compraRepository.save(this.compraMapper.map(compraRequestDTO));
        }catch(Exception e){
            return "ERROR: Compra fallida";
        }


        //PERSISTEMOS DETALLES
        compraDB.getDetalles().forEach(e->{
            this.detalleRepository.save(e);
        });

        Long NroOperacion = saveOperacion(compraDB,tarjeta);


        if(!emailService.sendCompraMessage(tarjeta.getNombre_completo_titular(),compraRequestDTO.getMonto(),compraRequestDTO.getDetalles(),NroOperacion.toString(),tarjeta.getCashHolder().getEmail()))
            return "ERROR: Compra fallida, notificacion no enviada";


        return "INFO: Compra existosa. Mail enviado con detalle de su compra";
    }


    //OPERACION GUARDAR
    private Long saveOperacion(Compra compra, Tarjeta tarjeta){

        Operacion operacion = new Operacion();
        operacion.setCompra(compra);
        operacion.setTarjeta(tarjeta);
        operacion.setFechaOperacion(LocalDateTime.now());

        Operacion operaciondb = this.operacionRespository.save(operacion);

        return operaciondb.getId();
    }



    @Override
    public OperacionResponseDTO findOperacion(Long nroOperacion)  {

        Operacion operacion = this.operacionRespository.findById(nroOperacion).get();


        switch (operacion.getTarjeta().getMarca().getNombre().toUpperCase()) {
            case "VISA" :
                return new OperacionResponseDTO(new Visa().calcularTasa(operacion.getFechaOperacion().toLocalDate()),operacion.getCompra().getMonto(),"VISA");
            case "NARA" :
                return new OperacionResponseDTO(new Nara().calcularTasa(operacion.getFechaOperacion().toLocalDate()),operacion.getCompra().getMonto(),"NARA");
            case "AMEX" :
                return new OperacionResponseDTO(new Amex().calcularTasa(operacion.getFechaOperacion().toLocalDate()),operacion.getCompra().getMonto(),"AMEX");
            default: return null;
        }

    }

}
