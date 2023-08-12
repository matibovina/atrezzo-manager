package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.EventStatus;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventEntity extends BaseEntity{
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

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
}
