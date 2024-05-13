package com.evgenygroupproject.spring.springboot.automatic_verification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Automatic verification",
				description = "Automatic verification", version = "2.0.0"
		)
)

@SpringBootApplication
public class AutomaticVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomaticVerificationApplication.class, args);
	}
}
