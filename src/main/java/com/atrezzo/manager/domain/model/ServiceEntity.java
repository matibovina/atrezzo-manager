package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.ServiceCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ServiceCategory category;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ServiceWorkerPriceEntity> serviceWorkerPrices;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<WorkerServiceEntity> workers;

}
