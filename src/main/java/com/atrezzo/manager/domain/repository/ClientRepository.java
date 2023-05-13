package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByUser(UserEntity userEntity);

    Optional<ClientEntity> findByEmail(String email);

    Optional<ClientEntity> findByLegalName(String legalName);

    Optional<ClientEntity> findByCompanyName(String companyName);

    Optional<ClientEntity> findByCuitNumber(String cuit);


}
