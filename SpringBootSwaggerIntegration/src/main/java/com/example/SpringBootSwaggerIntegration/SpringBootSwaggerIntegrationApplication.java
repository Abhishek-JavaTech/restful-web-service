package com.example.SpringBootSwaggerIntegration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                description = "descr goes here",
                version = "V1",
                title = "title goes here",
                summary = "summary goes here",
                contact = @Contact(
                        url = "url goes here",
                        email = "email goes here",
                        name = "name goes here"
                )
        ),
        servers =
        @Server(
                url = "http://localhost:8080/",
                description = "DEV"
        )

)
@SpringBootApplication
public class SpringBootSwaggerIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSwaggerIntegrationApplication.class, args);
    }

}
