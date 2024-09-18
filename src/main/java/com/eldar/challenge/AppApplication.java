package com.eldar.challenge;

import com.eldar.challenge.Repository.PersonaRepository;
import com.eldar.challenge.Repository.TarjetaRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.eldar.challenge.Service.Implements.TarjetaServiceImplements.desencriptar;
import static com.eldar.challenge.Service.Implements.TarjetaServiceImplements.encriptar;

@SpringBootApplication
@ComponentScan(basePackages = "com.eldar.challenge")
public class AppApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext ctx = SpringApplication.run(AppApplication.class, args);

		TarjetaRepository p = ctx.getBean(TarjetaRepository.class);
		System.out.println(p.findByNumero(5301682132884790L));

	}

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public void run(String... args) throws Exception {

	}
}
