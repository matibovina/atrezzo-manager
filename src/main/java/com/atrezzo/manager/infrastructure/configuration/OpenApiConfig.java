package com.atrezzo.manager.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Atrezzo Manager", version = "1.0", description = "Api para atrezzo Manager"))
public class OpenApiConfig {
}
