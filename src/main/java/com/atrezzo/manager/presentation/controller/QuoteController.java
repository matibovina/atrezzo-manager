package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuoteController {

    ResponseEntity<QuoteDTO> createQuote(QuoteDTO quoteDTO);

    ResponseEntity<List<QuoteDTO>> findAllQuotes();

    ResponseEntity<QuoteDTO> findQuoteById(Long id);

    ResponseEntity<List<QuoteDTO>> findAllQuotesByClientId(Long id);

    ResponseEntity<List<QuoteDTO>> findQuotesByStatus(QuoteStatus status);

    ResponseEntity<QuoteDTO> updateQuote(QuoteDTO quoteDTO);

    ResponseEntity<?> deleteQuoteById(Long id);

}
