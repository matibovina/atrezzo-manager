package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
