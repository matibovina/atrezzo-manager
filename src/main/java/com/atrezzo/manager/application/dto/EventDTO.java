package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.EventSessionEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.enums.EventStatus;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class EventDTO {

    private Long id;

    private QuoteDTO quote;

    private List<EventSessionDTO> eventSessions;

    private Double totalIncome;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Map<WorkerDTO, Double> workerSalary;

    private Double totalWorkersSalary;

    private Long clientId;

    private EventStatus eventStatus;

}
