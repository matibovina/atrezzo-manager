package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.EventDTO;
import com.atrezzo.manager.application.service.EventService;
import com.atrezzo.manager.domain.model.enums.EventStatus;
import com.atrezzo.manager.presentation.controller.EventController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventControllerImpl implements EventController {

    private final EventService eventService;

    @Override
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/client/{id}")
    public ResponseEntity<List<EventDTO>> findEventsByClientId(@PathVariable Long id) {
        return new ResponseEntity<>(eventService.findEventByClientId(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/clients")
    public ResponseEntity<List<EventDTO>> findEventsByClientAndStatus(@RequestParam EventStatus status,@RequestParam Long clientId) {
        return new ResponseEntity<>(eventService.findEventsByStatusAndClientId(status, clientId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/status")
    public ResponseEntity<List<EventDTO>> findEventsByStatus(@RequestParam EventStatus status) {
        return new ResponseEntity<>(eventService.findEventsByStatus(status), HttpStatus.OK);
    }

    @Override
    @GetMapping("/dates")
    public ResponseEntity<List<EventDTO>> findEventsByDate(@RequestParam LocalDateTime dateFrom,@RequestParam LocalDateTime dateTo) {
        return new ResponseEntity<>(eventService.findEventsByDate(dateFrom, dateTo), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTO eventDTO) {
        return new ResponseEntity<>(eventService.updateEvent(eventDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
