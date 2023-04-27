package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

}
