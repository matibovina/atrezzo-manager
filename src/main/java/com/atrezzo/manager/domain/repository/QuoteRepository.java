package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

   QuoteEntity findByClientId(Long id);
   List<QuoteEntity> findAllByClient_Id(Long clientId);
   List<QuoteEntity> findAllByStatus(QuoteStatus status);

}
