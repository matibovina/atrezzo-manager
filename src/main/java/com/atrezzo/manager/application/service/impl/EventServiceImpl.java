package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.EventDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.application.service.EventService;
import com.atrezzo.manager.domain.model.EventEntity;
import com.atrezzo.manager.domain.model.EventSessionEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.EventStatus;
import com.atrezzo.manager.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public EventDTO createEvent(QuoteDTO quote) {
        if(!quote.getStatus().getQuoteStatus().equals("accepted")){
            throw new IllegalArgumentException("Quote is not accepted yet");
        }

        QuoteEntity quoteEntity = modelMapper.map(quote, QuoteEntity.class);



        EventEntity event = new EventEntity();
        event.setQuote(quoteEntity);
        event.setClientId(quote.getClient().getId());








        return null;
    }

    @Override
    public EventDTO updateEvent(EventDTO event) {
        return null;
    }

    @Override
    public EventDTO findById(Long id) {
        return null;
    }

    @Override
    public List<EventDTO> findAll() {
        return null;
    }

    @Override
    public List<EventDTO> findEventsByDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return null;
    }

    @Override
    public List<EventDTO> findEventByClientId(Long clientId) {
        return null;
    }

    @Override
    public List<EventDTO> findEventsByStatusAndClientId(EventStatus status, Long clientId) {
        return null;
    }

    @Override
    public List<EventDTO> findEventsByStatus(EventStatus status) {
        return null;
    }

    @Override
    public void deleteEventById(Long id) {

    }
}
