package com.atrezzo.manager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_service_price")
    private Double totalServicePrice;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "quoteSession", cascade = CascadeType.ALL)
    private List<SessionServiceEntity> sessionServices;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private QuoteEntity quote;


    /*REVISAR METODO*/
    public int calculateDuration() {
        if (startTime == null || endTime == null) {
            throw new IllegalStateException("startTime and endTime must not be null");
        }

        int diffInSeconds = 100;
        return diffInSeconds;
    }
}
