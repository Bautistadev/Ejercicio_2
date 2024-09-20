package com.eldar.challenge.Service.Implements;

import com.eldar.challenge.DTO.CompraDTO;
import com.eldar.challenge.DTO.CompraRequestDTO;
import com.eldar.challenge.DTO.OperacionResponseDTO;
import com.eldar.challenge.Entities.*;
import com.eldar.challenge.Repository.CompraRepository;
import com.eldar.challenge.Repository.DetalleRepository;
import com.eldar.challenge.Repository.OperacionRespository;
import com.eldar.challenge.Repository.TarjetaRepository;
import com.eldar.challenge.Service.EmailService;
import com.eldar.challenge.Service.Interface.OperacionService;
import com.eldar.challenge.Service.Interface.TarjetaService;
import com.eldar.challenge.Service.Mapper.CompraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.eldar.challenge.Utils.EncriptyClass.desencriptar;


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
    public CompraDTO compra(CompraRequestDTO compraRequestDTO) throws Exception {

        Tarjeta tarjeta = findTarjetaByPam(compraRequestDTO.getPam());

        //GUARDAMOS LA COMPRA
        Compra compraDB = this.compraRepository.save(this.compraMapper.map(compraRequestDTO));

        //PERSISTEMOS DETALLES
        compraDB.getDetalles().forEach(e->{
            this.detalleRepository.save(e);
        });

        Long NroOperacion = saveOperacion(compraDB,tarjeta);


        emailService.sendCompraMessage(tarjeta.getNombre_completo_titular(),compraRequestDTO.getMonto(),compraRequestDTO.getDetalles(),NroOperacion.toString(),tarjeta.getCashHolder().getEmail());

        CompraDTO compraDTO = this.compraMapper.map(compraDB);
        compraDTO.setTitular(tarjeta.getNombre_completo_titular());

        return compraDTO;
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
    public List<OperacionResponseDTO> findOperacion(BigDecimal monto, String marca)  {

        List<LocalDate> localDate =  this.operacionRespository.getOperationDateByMontoAndMarca(monto,marca).stream().map(e->e.toLocalDateTime().toLocalDate()).collect(Collectors.toList());

        switch (marca.toUpperCase()) {
            case "VISA" :
                return localDate.stream().map(e->new OperacionResponseDTO(new Visa().calcularTasa(e),monto,"VISA")).collect(Collectors.toList());
            case "NARA" :
                return localDate.stream().map(e->new OperacionResponseDTO(new Nara().calcularTasa(e),monto,"NARA")).collect(Collectors.toList());
            case "AMEX" :
                return localDate.stream().map(e->new OperacionResponseDTO(new Amex().calcularTasa(e),monto,"AMEX")).collect(Collectors.toList());
            default: return null;
        }

    }

    @Override
    public Tarjeta findTarjetaByPam(Long pam) {
        try {
            return this.tarjetaRepository.findByNumero(pam).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean validateCVV(Long pam, String CVV) throws Exception {
        Tarjeta tarjeta = this.tarjetaRepository.findByNumero(pam).get();
        return CVV.equals(desencriptar(tarjeta.getCVV()));
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
