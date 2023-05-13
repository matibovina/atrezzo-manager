package com.atrezzo.manager.infrastructure.persistence;

import com.atrezzo.manager.domain.model.enums.TaxCondition;
import com.atrezzo.manager.infrastructure.persistence.AddressEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workers")
public class WorkerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String cuitNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    private TaxCondition taxCondition;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "client_user_id")
    private UserEntity user;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String profilePicture;

    // private List<ServiceEntity> services;
    //private List<EventEntity> events;
    // private List<SessionServiceEntity> sessionServices;
    // private List<InvoiceEntity> invoices;
}

