package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.infrastructure.persistence.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
