package com.atrezzo.manager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "quote_id")
    private QuoteEntity quote;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventSessionEntity> eventSessions;

    private Double totalIncome;

    @ElementCollection
    private Map<WorkerEntity, Double> workerSalary;

    private Double totalWorkersSalary;

    private Long clientId;

    private Boolean delivered;
}
