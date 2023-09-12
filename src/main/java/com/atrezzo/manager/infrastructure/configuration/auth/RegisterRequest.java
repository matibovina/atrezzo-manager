package com.atrezzo.manager.infrastructure.configuration.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long id;
    private String username;
    private String email;
    private String password;

}
