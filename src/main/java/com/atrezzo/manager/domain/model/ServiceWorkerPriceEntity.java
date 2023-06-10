package com.atrezzo.manager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_worker_price")
public class ServiceWorkerPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    private Integer hoursQuantity;

    private Double pricePerHour;

    private Double workerSalary;

    private Double totalServicePerHour;

    public Double calculateTotalPrice() {
        totalServicePerHour = pricePerHour * hoursQuantity;
        return totalServicePerHour;
    }
}

