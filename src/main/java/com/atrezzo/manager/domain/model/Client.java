package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.TaxCondition;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long id;

    private String companyName;

    private String legalName;

    private String cuitNumber;

    @Enumerated(EnumType.STRING)
    private TaxCondition taxCondition;

    private String email;

    private String phone;

    private Address address;

    private User user;

    private String profilePicture;

    //private List<Contact> contacts;

    //private List<Quotes> quotes;

    //private List<Invoice> invoices;

    //private List<Event> events;


}
