package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.application.service.QuoteService;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import com.atrezzo.manager.domain.model.ClientEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.presentation.controller.QuoteController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class QuoteControllerImpl implements QuoteController {

    private final QuoteService quoteService;


    @Override
    @PostMapping
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO quoteDTO)
    throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(quoteService.createQuote(quoteDTO), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<QuoteDTO>> findAllQuotes() throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(quoteService.getAllQuotes(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/quote/{id}")
    public ResponseEntity<QuoteDTO> findQuoteById(@PathVariable Long id)throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(quoteService.findQuoteById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/quotes/client/{id}")
    public ResponseEntity<List<QuoteDTO>> findAllQuotesByClientId(@PathVariable("id") Long clientId) {
        return new ResponseEntity<>(quoteService.findAllQuotesByClientId(clientId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/quotes/status/{status}")
    public ResponseEntity<List<QuoteDTO>> findQuotesByStatus(@PathVariable QuoteStatus status)
            throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(quoteService.findQuoteByStatus(String.valueOf(status)), HttpStatus.OK);
    }


    @Override
    @PutMapping("/quote")
    public ResponseEntity<QuoteDTO> updateQuote(@RequestBody QuoteDTO quoteDTO)
            throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(quoteService.updateQuote(quoteDTO), HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/quote/{id}")
    public ResponseEntity<?> deleteQuoteById(@PathVariable Long id)
            throws NoClassFoundException, CustomIllegalArgumentException {
        quoteService.deleteQuote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
