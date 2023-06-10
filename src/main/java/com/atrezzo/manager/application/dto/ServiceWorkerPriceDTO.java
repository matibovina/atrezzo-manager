package com.atrezzo.manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceWorkerPriceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1366540298882265263L;
    private Long id;
    private ServiceDTO service;
    private Integer hoursQuantity;
    private Double pricePerHour;
    private Double workerSalary;
    private Double totalServicePerHour;

}
