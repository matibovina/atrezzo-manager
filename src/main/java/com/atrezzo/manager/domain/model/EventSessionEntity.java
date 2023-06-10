package com.atrezzo.manager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_sessions")
public class EventSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private QuoteEntity quote;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @OneToMany(mappedBy = "eventSession", cascade = CascadeType.ALL)
    private List<SessionServiceEntity> sessionServices;

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
