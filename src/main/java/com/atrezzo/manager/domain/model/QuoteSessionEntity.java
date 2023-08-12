package com.atrezzo.manager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quote_sessions")
public class QuoteSessionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_service_price")
    private Double totalServicePrice;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @OneToMany(mappedBy = "quoteSession", cascade = CascadeType.ALL)
    private List<SessionServiceEntity> sessionServices;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private QuoteEntity quote;

    public Long calculateDuration() {
        if (startTime == null || endTime == null) {
            // Aquí deberías manejar esta situación, quizás lanzando una excepción.
            throw new IllegalStateException("startTime and endTime must not be null");
        }
        long diffInMillies = Math.abs(endTime.toNanoOfDay() - startTime.toNanoOfDay());
        long diffInSeconds = diffInMillies / 1_000_000_000;
        return diffInSeconds;
    }
}
