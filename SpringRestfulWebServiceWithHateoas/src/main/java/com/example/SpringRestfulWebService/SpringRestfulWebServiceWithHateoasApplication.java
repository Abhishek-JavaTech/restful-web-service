package com.example.SpringRestfulWebService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				summary = "Restful web service implementation",
				description = "this webservice implementation contains GET / POST / PUT / DELETE / PATCH request, try accessing swagger url as below \n" +
						"http://localhost:8080/swagger-ui/index.html",

				contact = @Contact(
						name = "Abhishek Rangari",
						email = "rangari.javatech@gmail.com"
				),
				title = "Spring Restful Web Service With Hateoas",
				version = "V1"
		)
)
@SpringBootApplication
public class SpringRestfulWebServiceWithHateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestfulWebServiceWithHateoasApplication.class, args);
	}

}
