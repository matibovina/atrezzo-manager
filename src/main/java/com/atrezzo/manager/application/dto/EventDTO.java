package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.EventSessionEntity;
import com.atrezzo.manager.domain.model.QuoteEntity;
import com.atrezzo.manager.domain.model.WorkerEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;


public class EventDTO {

    private Long id;

    private QuoteEntity quote;

    private List<EventSessionEntity> eventSessions;

    private Double totalIncome;

    private Map<WorkerEntity, Double> workerSalary;

    private Double totalWorkersSalary;

    private Long clientId;

    private Boolean delivered;
}
