package com.atrezzo.manager.infrastructure.configuration.auth;

import com.atrezzo.manager.domain.model.UserEntity;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.repository.UserRepository;
import com.atrezzo.manager.infrastructure.configuration.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

   private final UserRepository userRepository;
   private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {




        return AuthResponse.builder()
                .token(null)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(Roles.ROLE_ADMIN)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
