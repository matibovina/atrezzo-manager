package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

   QuoteEntity findByClientId(Long id);
   List<QuoteEntity> findAllByClientId(Long id);
   List<QuoteEntity> findAllByStatus(String status);

}
