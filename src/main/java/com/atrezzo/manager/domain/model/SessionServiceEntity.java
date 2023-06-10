package com.atrezzo.manager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "session_services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_service_price")
    private Double totalServicePrice;

    @Column(name = "worker_salary")
    private Double workerSalary;
    @ManyToOne
    @JoinColumn(name = "event_session_id")
    private EventSessionEntity eventSession;

    @Column(name = "duration_hours")
    private Integer durationHours;

    public Double calculateTotalServicePrice() {
        // Placeholder for calculating the total service price.
        // You need to implement your own business logic here.
        return null;
    }

    public Double calculateWorkerSalary() {
        // Placeholder for calculating the worker salary.
        // You need to implement your own business logic here.
        return null;
    }
}
