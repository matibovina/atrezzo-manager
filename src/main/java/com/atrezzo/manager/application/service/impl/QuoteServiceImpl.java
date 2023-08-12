package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.application.service.QuoteService;
import com.atrezzo.manager.domain.model.QuoteSessionEntity;
import com.atrezzo.manager.domain.model.SessionServiceEntity;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import com.atrezzo.manager.domain.repository.ClientRepository;
import com.atrezzo.manager.domain.repository.QuoteRepository;
import com.atrezzo.manager.domain.model.ClientEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.repository.QuoteSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    private ModelMapper  modelMapper = new ModelMapper();

    private final ClientRepository clientRepository;

    private final QuoteSessionRepository quoteSessionRepository;




    @Override
    public QuoteDTO createQuote(QuoteDTO quote) {
        QuoteDTO quoteDTO = quote;

        if(quote == null) {
            throw new IllegalArgumentException("Quote can't be null");
        }


        QuoteEntity savedQuote = new QuoteEntity();
        savedQuote.setClient(clientRepository.findById(quoteDTO.getClient().getId()).orElseThrow());

        QuoteSessionEntity quoteSession = new QuoteSessionEntity();

        SessionServiceEntity sessionService = new SessionServiceEntity();

        try {
            savedQuote = quoteRepository.save(modelMapper.map(quote, QuoteEntity.class));
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while saving Quote");
        }
        return modelMapper.map(savedQuote, QuoteDTO.class);
    }

    @Override
    public List<QuoteDTO> getAllQuotes() {

        List<QuoteEntity> quoteEntities = quoteRepository.findAll();

        return quoteEntities.stream().map(
                quoteEntity -> modelMapper.map(quoteEntity, QuoteDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public QuoteDTO findQuoteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        QuoteEntity foundQuote = quoteRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Quote not found")
            );

        return modelMapper.map(foundQuote, QuoteDTO.class);
    }

    @Override
    public List<QuoteDTO> findAllQuotesByClientId(Long clientId) {
        if(clientId == null) {
            throw new IllegalArgumentException("Client can't be null");
        }
        System.out.println(clientId);
/*        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));*/
        System.out.println("numero cliente: " + clientId);

        List<QuoteEntity> quotes = quoteRepository.findAllByClient_Id(clientId);
        System.out.println("hola");
        return quotes.stream().map(quote -> modelMapper.map(quote, QuoteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<QuoteDTO> findQuoteByStatus(String status) {
        if(status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        QuoteStatus quoteStatus = QuoteStatus.valueOf(status.toUpperCase());

        List<QuoteEntity> quotes = quoteRepository.findAllByStatus(quoteStatus);
        return quotes.stream().map(quote -> modelMapper.map(quote, QuoteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public QuoteDTO updateQuote(QuoteDTO quote) {
        if(quote == null || quote.getId() == null) {
            throw new IllegalArgumentException("Quote can't be null");
        }

        QuoteEntity quoteEntity = quoteRepository.findById(quote.getId())
                .orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        quoteEntity.setStatus(quote.getStatus());

        QuoteEntity updatedQuote = quoteRepository.save(quoteEntity);
        return modelMapper.map(updatedQuote, QuoteDTO.class);

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
