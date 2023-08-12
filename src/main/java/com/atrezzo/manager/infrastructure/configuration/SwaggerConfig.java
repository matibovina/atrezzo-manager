package com.atrezzo.manager.infrastructure.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi usersApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi rolesApi() {
        return GroupedOpenApi.builder()
                .group("roles")
                .pathsToMatch("/api/roles/**")
                .build();
    }

    @Bean
    public GroupedOpenApi clientsApi() {
        return GroupedOpenApi.builder()
                .group("clients")
                .pathsToMatch("/api/clients/**")
                .build();
    }

    @Bean
    public GroupedOpenApi contactsApi() {
        return GroupedOpenApi.builder()
                .group("contacts")
                .pathsToMatch("/api/contacts/**")
                .build();
    }

    @Bean
    public GroupedOpenApi workerApi() {
        return GroupedOpenApi.builder()
                .group("workers")
                .pathsToMatch("/api/workers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi serviceApi() {
        return GroupedOpenApi.builder()
                .group("services")
                .pathsToMatch("/api/services/**")
                .build();
    }

}
