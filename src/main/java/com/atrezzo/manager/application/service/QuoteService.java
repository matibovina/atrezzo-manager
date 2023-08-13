package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.domain.model.ClientEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;

import java.util.List;

public interface QuoteService {

    QuoteDTO createQuote(QuoteDTO quote);
    List<QuoteDTO> getAllQuotes();
    QuoteDTO findQuoteById(Long id);
    List<QuoteDTO> findAllQuotesByClientId(Long clientId);
    List<QuoteDTO> findQuoteByStatus(String status);
    QuoteDTO updateQuote(QuoteDTO quoteDTO);
    void deleteQuote(Long id);



}
