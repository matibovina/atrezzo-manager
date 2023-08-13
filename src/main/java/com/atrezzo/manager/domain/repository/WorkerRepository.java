package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  WorkerRepository extends JpaRepository<WorkerEntity, Long> {

    WorkerEntity findByEmail(String email);
    WorkerEntity findByCuitNumber(String cuitNumber);
    WorkerEntity findByUserUsername(String username);

}
