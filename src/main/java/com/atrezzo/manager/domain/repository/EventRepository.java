package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.EventEntity;
import com.atrezzo.manager.domain.model.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findBySessionsDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
    List<EventEntity> findByClientId(Long clientId);
    List<EventEntity> findByStatusAndClientId(EventStatus status, Long clientId);
    List<EventEntity> findByStatus(EventStatus status);

}
