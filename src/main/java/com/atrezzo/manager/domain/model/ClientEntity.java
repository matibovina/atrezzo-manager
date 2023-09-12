package com.atrezzo.manager.domain.model;


import com.atrezzo.manager.domain.model.enums.TaxCondition;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String legalName;

    private String cuitNumber;

    @Enumerated(EnumType.STRING)
    private TaxCondition taxCondition;

    @Email
    private String email;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne
    @JoinColumn(name = "client_user_id")
    private UserEntity user;

    private String profilePicture;

    private Boolean enabled;

    @OneToMany(mappedBy = "client")
    private List<ContactEntity> contacts;

    @OneToMany(mappedBy = "client")
    private List<QuoteEntity> quotes;

    //private List<InvoiceEntity> invoices;

    //private List<EventEntity> events;

}
