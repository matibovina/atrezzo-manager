package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
