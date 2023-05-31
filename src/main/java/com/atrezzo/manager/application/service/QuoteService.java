package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.QuoteEntity;

import java.util.List;

public interface QuoteService {

    QuoteEntity createQuote(QuoteEntity quote);
    List<QuoteEntity> getAllQuotes();
    QuoteEntity findQuoteById(Long id);
    QuoteEntity findQuoteByClient(ClientEntity client);
    List<QuoteEntity> findAllQuotesByClientId(Long clientId);
    List<QuoteEntity> findQuoteByStatus(String status);
    QuoteEntity updateQuote(QuoteEntity quoteDTO);
    void deleteQuote(Long id);



}
