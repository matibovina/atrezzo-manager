package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.QuoteSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteSessionRepository extends JpaRepository<QuoteSessionEntity, Long> {
}
