package com.eldar.challenge.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger_ui_configutarion {
    @Bean
    public OpenAPI customUIOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Challenge Eldar ejercicio 2 ")
                        .version("1.0.0")
                        .description("Operacion con tarjetas API REST")
                        .contact(new Contact()
                                .name("Bautista Basilio")
                                .email("BautistaBasilioDev@outlook.com")
                        )
                );




    }
}
