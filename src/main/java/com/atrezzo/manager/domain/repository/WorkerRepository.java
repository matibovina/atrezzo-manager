package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.infrastructure.persistence.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

    WorkerEntity findByEmail(String email);
    WorkerEntity findByCuitNumber(String cuitNumber);
    WorkerEntity findByUserUsername(String username);

}
