package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.ClientEntity;
import com.atrezzo.manager.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByUser(UserEntity userEntity);

    Optional<ClientEntity> findByEmail(String email);

    Optional<ClientEntity> findByLegalName(String legalName);

    Optional<ClientEntity> findByCompanyName(String companyName);

    Optional<ClientEntity> findByCuitNumber(String cuit);


}
