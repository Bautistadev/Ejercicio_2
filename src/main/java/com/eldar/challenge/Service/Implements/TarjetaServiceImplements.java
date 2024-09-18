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

@Service
public class TarjetaServiceImplements implements TarjetaService {


    private TarjetaMapper tarjetaMapper;
    private TarjetaRepository tarjetaRepository;
    private PersonaRepository personaRepository;

    private EmailService emailService;

    public TarjetaServiceImplements(TarjetaMapper tarjetaMapper,TarjetaRepository tarjetaRepository,PersonaRepository personaRepository,EmailService emailService) {
        this.tarjetaMapper = tarjetaMapper;
        this.tarjetaRepository = tarjetaRepository;
        this.personaRepository = personaRepository;
        this.emailService = emailService;
    }

    @Override
    public String save(TarjetaRequestDTO tarjetaRequestDTO) throws Exception {

        Tarjeta tarjetaSave = this.tarjetaMapper.map(tarjetaRequestDTO);
        Persona persona = this.personaRepository.findByDni(tarjetaRequestDTO.getDNI()).get();

        Long pam = generarNumeroDe16Cifras();
        Integer cvv = generarNumeroDeCVV();

        tarjetaSave.setNumero(pam);
        tarjetaSave.setCVV(encriptar(cvv.toString()));
        tarjetaSave.setFecha_vencimiento(crearFechaVencimiento(LocalDate.now(),48));
        tarjetaSave.setCashHolder(persona);

        this.tarjetaRepository.save(tarjetaSave);

        this.emailService.sendInfoTarjetaMessage(tarjetaRequestDTO.getNombre_completo_titular(),formatearNumeroTarjeta(pam.toString()),cvv.toString(),tarjetaRequestDTO.getMarca(),persona.getEmail());

        return "Tu operación ha sido procesada exitosamente. ¡Gracias por confiar en nosotros!. Los datos de tu tarjeta se verán reflejados en tu correo electrónico";
    }

    public Long generarNumeroDe16Cifras() {
        Random random = new Random();

        // Generar un número aleatorio de 16 cifras
        long numero = 1000000000000000L + (long)(random.nextDouble() * 9000000000000000L);

        return numero;
    }

    public Integer generarNumeroDeCVV() {
        Random random = new Random();

        // Generar un número aleatorio de 3 cifras
        return 100 + random.nextInt(900);
    }

    public LocalDate crearFechaVencimiento(LocalDate fechaInicial, int meses) {
        // Suma los meses a la fecha inicial
        return fechaInicial.plusMonths(meses);
    }

    public static String encriptar(String CVV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, crearClave("1234567812345678"));
        byte[] encrypted = cipher.doFinal(CVV.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String desencriptar(String encriptadoCVV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, crearClave("1234567812345678"));

        byte[] datosDescifrados = cipher.doFinal(Base64.getDecoder().decode(encriptadoCVV));
        return new String(datosDescifrados);
    }

    private static SecretKeySpec crearClave(String claveSecreta) throws Exception {
        byte[] key = claveSecreta.getBytes("UTF-8");
        return new SecretKeySpec(key, "AES");
    }

    public static String formatearNumeroTarjeta(String numero) {
        // Verifica que el número tenga 16 cifras
        if (numero == null || numero.length() != 16 || !numero.matches("\\d+")) {
            throw new IllegalArgumentException("El número de tarjeta debe tener 16 cifras.");
        }

        // Usa expresiones regulares para insertar los guiones en la posición correcta
        return numero.replaceAll("(\\d{4})(\\d{4})(\\d{4})(\\d{4})", "$1-$2-$3-$4");
    }
}
