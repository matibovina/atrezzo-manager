package com.atrezzo.manager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class AtrezzoManagerApplication {

	public static void main(String[] args) {

		SpringApplication.run(AtrezzoManagerApplication.class, args);
	}

}
