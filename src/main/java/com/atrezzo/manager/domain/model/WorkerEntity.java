package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.TaxCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workers")
public class WorkerEntity extends BaseEntity{

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
    @JoinColumn(name = "worker_user_id")
    private UserEntity user;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String profilePicture;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WorkerServiceEntity> services;
    //private List<EventEntity> events;
    // private List<SessionServiceEntity> sessionServices;
    // private List<InvoiceEntity> invoices;
}

