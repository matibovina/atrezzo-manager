package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.application.service.QuoteService;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import com.atrezzo.manager.domain.repository.ClientRepository;
import com.atrezzo.manager.domain.repository.QuoteRepository;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.QuoteEntity;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    private ModelMapper  modelMapper = new ModelMapper();

    private ClientRepository clientRepository;


    @Override
    public QuoteEntity createQuote(QuoteEntity quote) {
        QuoteEntity quoteEntity = modelMapper.map(quote, QuoteEntity.class);

        if(quoteEntity == null) {
            throw new IllegalArgumentException("Quote can't be null");
        }
        QuoteEntity savedQuote;
        try {
            savedQuote = quoteRepository.save(quoteEntity);
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while saving Quote");
        }
        return modelMapper.map(quoteEntity, QuoteEntity.class);
    }

    @Override
    public List<QuoteEntity> getAllQuotes() {

        List<QuoteEntity> quoteEntities = quoteRepository.findAll();

        return quoteEntities.stream().map(
                quoteEntity -> modelMapper.map(quoteEntity, QuoteEntity.class)
        ).collect(Collectors.toList());
    }

    @Override
    public QuoteEntity findQuoteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        QuoteEntity foundQuote = quoteRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Quote not found")
            );

        return modelMapper.map(foundQuote, QuoteEntity.class);
    }

    @Override
    public QuoteEntity findQuoteByClient(ClientEntity client) {
        if(client == null || client.getId() == null) {
            throw new IllegalArgumentException("Client can't be null");
        }

        ClientEntity clientEntity = clientRepository.findById(client.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        QuoteEntity quote = quoteRepository.findByClientId(clientEntity.getId());

        return modelMapper.map(quote, QuoteEntity.class);
    }

    @Override
    public List<QuoteEntity> findAllQuotesByClientId(Long clientId) {
        if(clientId == null) {
            throw new IllegalArgumentException("Client can't be null");
        }

        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        List<QuoteEntity> quotes = quoteRepository.findAllByClientId(clientEntity.getId());

        return quotes.stream().map(quote -> modelMapper.map(quote, QuoteEntity.class)).collect(Collectors.toList());
    }

    @Override
    public List<QuoteEntity> findQuoteByStatus(String status) {
        if(status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }

        List<QuoteEntity> quotes = quoteRepository.findAllByStatus(status);

        return quotes.stream().map(quote -> modelMapper.map(quote, QuoteEntity.class)).collect(Collectors.toList());
    }

    @Override
    public QuoteEntity updateQuote(QuoteEntity quote) {
        if(quote == null || quote.getId() == null) {
            throw new IllegalArgumentException("Quote can't be null");
        }

        QuoteEntity quoteEntity = quoteRepository.findById(quote.getId())
                .orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        quoteEntity.setStatus(quote.getStatus());

        QuoteEntity updatedQuote = quoteRepository.save(quoteEntity);
        return modelMapper.map(updatedQuote, QuoteEntity.class);

    }

    @Override
    public void deleteQuote(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        QuoteEntity quoteEntity = quoteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        quoteRepository.delete(quoteEntity);
    }
}
