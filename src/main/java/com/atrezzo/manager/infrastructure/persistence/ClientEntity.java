package com.atrezzo.manager.infrastructure.persistence;


import com.atrezzo.manager.domain.model.Address;
import com.atrezzo.manager.domain.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    //private TaxCondition taxCondition;

    @Email
    private String email;

    private String phone;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne
    @JoinColumn(name = "client_user_id")
    private UserEntity user;

    private String profilePicture;

    //private List<ContactEntity> contacts;

    //private List<QuotesEntity> quotes;

    //private List<InvoiceEntity> invoices;

    //private List<EventEntity> events;

}
