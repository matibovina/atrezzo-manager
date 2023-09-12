package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.EventDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.application.service.EventService;
import com.atrezzo.manager.domain.model.EventEntity;
import com.atrezzo.manager.domain.model.EventSessionEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.EventStatus;
import com.atrezzo.manager.domain.repository.EventRepository;
import com.atrezzo.manager.domain.repository.QuoteRepository;
import com.atrezzo.manager.infrastructure.exceptions.NoClientsFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final QuoteRepository quoteRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public EventDTO createEvent(QuoteDTO quoteDTO) {

        QuoteEntity quote = modelMapper.map(quoteDTO, QuoteEntity.class);

        if(!quote.getStatus().getQuoteStatus().equals("accepted")){
            throw new IllegalArgumentException("Quote is not accepted yet");
        }

        EventEntity event = new EventEntity();
        event.setTotalAmount(quote.getTotalWithTax());
        event.setQuote(quote);
        event.setClientId(quote.getClient().getId());

        for (var quoteSession : quote.getQuoteSessions()) {
            var eventSession = new EventSessionEntity();
            eventSession.setEvent(event);
            eventSession.setTotalDuration(quoteSession.calculateDuration());
            eventSession.setStartTime(quoteSession.getStartTime());
            eventSession.setEndTime(quoteSession.getEndTime());
            eventSession.setSessionServices(new ArrayList<>(quoteSession.getSessionServices()));
        }

        eventRepository.save(event);


        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO) {

        if (eventDTO == null || !eventRepository.existsById(eventDTO.getId())) {
            throw new IllegalArgumentException("Event can't be null");
        }

        EventEntity event = modelMapper.map(eventDTO, EventEntity.class);

        eventRepository.save(event);


        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public EventDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        EventEntity event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event doesn't exists."));

        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(
                event -> modelMapper.map(event, EventDTO.class)
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> findEventsByDate(LocalDateTime dateFrom, LocalDateTime dateTo) {

        return eventRepository.findByCreatedAtBetween(dateFrom, dateTo).stream().map(
                event -> modelMapper.map(event, EventDTO.class)
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> findEventByClientId(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        return eventRepository.findByClientId(clientId).stream().map(
                event -> modelMapper.map(event, EventDTO.class)
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> findEventsByStatusAndClientId(EventStatus status, Long clientId) {
        return eventRepository.findByEventStatusAndClientId(status, clientId).stream().map(
                event -> modelMapper.map(event, EventDTO.class)
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> findEventsByStatus(EventStatus status) {
        return eventRepository.findByEventStatus(status).stream().map(
                event -> modelMapper.map(event, EventDTO.class)
        ).toList();
    }

    @Override
    public void deleteEventById(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new NoClientsFoundException("Event doesn't exists with id " + id);
        }
    }
}
