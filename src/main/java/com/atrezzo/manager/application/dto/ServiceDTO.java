package com.atrezzo.manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5076162765695855466L;

    private Long id;
    private String title;
    private String description;
    private List<ServiceWorkerPriceDTO> serviceWorkerPrices;
    private List<WorkerDTO> workers;

}
