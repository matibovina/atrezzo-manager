package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.EventDTO;
import com.atrezzo.manager.domain.model.enums.EventStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EventController {

    ResponseEntity<List<EventDTO>> getAllEvents();

    ResponseEntity<EventDTO> findById(Long id);

    ResponseEntity<List<EventDTO>> findEventsByClientId(Long id);

    ResponseEntity<List<EventDTO>> findEventsByClientAndStatus(EventStatus status, Long clientId);

    ResponseEntity<List<EventDTO>> findEventsByStatus(EventStatus status);

    ResponseEntity<List<EventDTO>> findEventsByDate(LocalDateTime dateFrom, LocalDateTime dateTo);

    ResponseEntity<EventDTO> updateEvent(EventDTO eventDTO);

    ResponseEntity<?> deleteEventById(Long id);



}
