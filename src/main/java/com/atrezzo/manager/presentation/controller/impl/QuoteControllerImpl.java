package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.application.service.QuoteService;
import com.atrezzo.manager.domain.model.Quote;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.QuoteEntity;
import com.atrezzo.manager.presentation.controller.QuoteController;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/quotes")
@RequiredArgsConstructor
public class QuoteControllerImpl implements QuoteController {

    private final ModelMapper modelMapper;

    private final QuoteService quoteService;


    @Override
    @PostMapping(value = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuote(@RequestBody QuoteDTO quoteDTO) {
        try {
            if (quoteDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Quote cannot be null.");
            }
            QuoteEntity quote = modelMapper.map(quoteDTO, QuoteEntity.class);
            QuoteDTO createdQuote = modelMapper.map(quoteService.createQuote(quote), QuoteDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuote);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the quote.");
        }
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllQuotes() {
        try {
            List<QuoteEntity> quotes = quoteService.getAllQuotes();
            List<QuoteDTO> quotesDTO = quotes.stream().map(
                    quote -> modelMapper.map(quote, QuoteDTO.class)
            ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(quotesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error has occurred while trying to get all quotes");
        }
    }

    @Override
    @GetMapping(value = "/quote/{id}")
    public ResponseEntity<?> findQuoteById(@PathVariable Long id) {
        try {
            QuoteEntity quote = quoteService.findQuoteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(quote, QuoteDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quote doesn't exist.");
        }
    }

    @Override
    @GetMapping(value = "/quote/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findQuoteByClient(@RequestBody ClientDTO clientDTO) {
        try {
            QuoteEntity quote = quoteService.findQuoteByClient(modelMapper.map(clientDTO, ClientEntity.class));
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(quote, QuoteDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quote doesn't exist.");
        }
    }

    @Override
    @GetMapping(value = "/quotes/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllQuotesByClientId(@PathVariable Long clientId) {
        try {
            List<QuoteEntity> quotes = quoteService.findAllQuotesByClientId(clientId);
            List<QuoteDTO> quotesDTO = quotes.stream().map(
                    quote -> modelMapper.map(quote, QuoteDTO.class)
            ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(quotesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quotes don't exist for this client.");
        }
    }

    @Override
    @GetMapping(value = "/quotes/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findQuotesByStatus(@PathVariable QuoteStatus status) {
        System.out.println("este es status " + status);
        System.out.println(status.name());
        try {
            List<QuoteEntity> quotes = quoteService.findQuoteByStatus(status.name());

            List<QuoteDTO> quotesDTO = quotes.stream().map(
                    quote -> modelMapper.map(quote, QuoteDTO.class)
            ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(quotesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No quotes found with this status.");
        }
    }


    @Override
    @PutMapping(value = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQuote(@RequestBody QuoteDTO quoteDTO) {
        try {
            QuoteEntity updatedQuote = quoteService.updateQuote(modelMapper.map(quoteDTO, QuoteEntity.class));
            QuoteDTO updatedQuoteDTO = modelMapper.map(updatedQuote, QuoteDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedQuoteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quote not found.");
        }
    }


    @Override
    @DeleteMapping(value = "/quote/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQuoteById(@PathVariable Long id) {
        try {
            quoteService.deleteQuote(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Quote with id " + id + " was successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quote doesn´t exists.");
        }
    }
}
