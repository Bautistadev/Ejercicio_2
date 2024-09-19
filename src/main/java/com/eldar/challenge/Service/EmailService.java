package com.eldar.challenge.Service;

import com.eldar.challenge.DTO.DetalleDTO;
import com.eldar.challenge.Entities.Detalle;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private TemplateEngine templateEngine;

    public Boolean sendInfoTarjetaMessage(String nombreCompleto,String numero,String cvv, String marca,String to) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

        Context context = new Context();
        context.setVariable("nombre_completo", nombreCompleto.toUpperCase());
        context.setVariable("numero", numero);
        context.setVariable("cvv", cvv);
        context.setVariable("marca", marca.toUpperCase());

        String htmlContent =  templateEngine.process("email-tarjeta-template",context);

        helper.setTo(to);
        helper.setSubject("Informacion de tarjeta de credito");
        helper.setText(htmlContent, true);  // El segundo parámetro indica que es HTML

        // Enviar el correo
        mailSender.send(message);

        return true;
    }

    public Boolean sendCompraMessage(String nombreCompleto, BigDecimal monto, List<DetalleDTO> detalle,String nroOperacion, String to) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

        Context context = new Context();
        context.setVariable("nombre_completo", nombreCompleto.toUpperCase());
        context.setVariable("monto", monto);
        context.setVariable("detalles",detalle);
        context.setVariable("nroOperacion",nroOperacion);

        String htmlContent =  templateEngine.process("email-compra-template",context);

        helper.setTo(to);
        helper.setSubject("Informacion de compra");
        helper.setText(htmlContent, true);  // El segundo parámetro indica que es HTML

        // Enviar el correo
        mailSender.send(message);

        return true;
    }
}
