package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import org.springframework.http.ResponseEntity;

public interface QuoteController {

    ResponseEntity<?> createQuote(QuoteDTO quoteDTO);

    ResponseEntity<?> findAllQuotes();

    ResponseEntity<?> findQuoteById(Long id);

    ResponseEntity<?> findQuoteByClient(ClientDTO clientDTO);

    ResponseEntity<?> findAllQuotesByClientId(Long id);

    ResponseEntity<?> findQuotesByStatus(QuoteStatus status);

    ResponseEntity<?> updateQuote(QuoteDTO quoteDTO);

    ResponseEntity<?> deleteQuoteById(Long id);

}
