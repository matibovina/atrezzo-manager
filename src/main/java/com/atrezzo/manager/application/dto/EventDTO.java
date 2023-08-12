package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.EventSessionEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.EventStatus;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class EventDTO {

    private Long id;

    private QuoteEntity quote;

    private List<EventSessionEntity> eventSessions;

    private Double totalIncome;

    private LocalDateTime createdAt;

    private Map<WorkerDTO, Double> workerSalary;

    private Double totalWorkersSalary;

    private Long clientId;

    private EventStatus eventStatus;

}
