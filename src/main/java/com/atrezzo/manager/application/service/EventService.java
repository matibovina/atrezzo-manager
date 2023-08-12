package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.EventDTO;
import com.atrezzo.manager.application.dto.QuoteDTO;
import com.atrezzo.manager.domain.model.EventEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventDTO createEvent(QuoteDTO quote);

    EventDTO updateEvent(EventDTO event);

    EventDTO findById(Long id);

    List<EventDTO> findAll();

    List<EventDTO> findEventsByDate(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<EventDTO> findEventByClientId(Long clientId);

    List<EventDTO> findEventsByStatusAndClientId(EventStatus status, Long clientId);

    List<EventDTO> findEventsByStatus(EventStatus status);

    void deleteEventById(Long id);

}
