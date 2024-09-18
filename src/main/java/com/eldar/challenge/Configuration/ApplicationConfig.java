package com.eldar.challenge.Configuration;

import com.eldar.challenge.Repository.*;
import com.eldar.challenge.Service.EmailService;
import com.eldar.challenge.Service.Implements.OperacionServiceImplements;
import com.eldar.challenge.Service.Interface.OperacionService;
import com.eldar.challenge.Service.Interface.PersonaService;
import com.eldar.challenge.Service.Interface.TarjetaService;
import com.eldar.challenge.Service.Mapper.CompraMapper;
import com.eldar.challenge.Service.Mapper.DetalleMapper;
import com.eldar.challenge.Service.Mapper.PersonaMapper;
import com.eldar.challenge.Service.Mapper.TarjetaMapper;
import com.eldar.challenge.Service.Implements.PersonaServiceImplements;
import com.eldar.challenge.Service.Implements.TarjetaServiceImplements;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class ApplicationConfig {

    @Bean
    public PersonaMapper personaMapper (){
        return new PersonaMapper();
    }

    @Bean
    public PersonaService personaService(PersonaMapper personaMapper, PersonaRepository personaRepository){
        return new PersonaServiceImplements(personaMapper,personaRepository);
    }

    @Bean
    public TarjetaMapper tarjetaMapper(){return new TarjetaMapper();}

    @Bean
    public TarjetaService tarjetaService(TarjetaMapper tarjetaMapper, TarjetaRepository tarjetaRepository, PersonaRepository personaRepository, EmailService emailService){
        return new TarjetaServiceImplements(tarjetaMapper,tarjetaRepository,personaRepository,emailService);
    }

    @Bean
    public CompraMapper compraMapper(DetalleMapper detalleMapper){
        return new CompraMapper(detalleMapper);
    }

    @Bean
    public OperacionService operacionService(CompraMapper compraMapper, CompraRepository compraRepository, DetalleRepository detalleRepository, TarjetaRepository tarjetaRepository, OperacionRespository operacionRespository){
        return new OperacionServiceImplements(compraMapper,compraRepository,detalleRepository,tarjetaRepository,operacionRespository);
    }

    @Bean
    public DetalleMapper detalleMapper(){
        return new DetalleMapper();
    }

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("batubasilio69@gmail.com");
        mailSender.setPassword("mugururmpmjfprnu");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
