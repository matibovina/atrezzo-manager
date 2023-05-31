package com.atrezzo.manager.infrastructure.persistence;


import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.Contact;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    private String location;
    private String description;
    private Double totalPrice;
    private Double taxPercentage;
    private Double taxAmount;
    private Double totalWithTax;

    @Enumerated(EnumType.STRING)
    private QuoteStatus status;

    @ManyToMany
    @JoinTable(
            name = "quote_contact",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<ContactEntity> contacts;
    //private Invoice invoice;
    //private List<ExtraItem> extraItems;
    //private Event event;
    //private List<EventSession> eventSessions;


    public void calculateTaxAndTotalWithTax() {
        taxAmount = totalPrice * (taxPercentage / 100);
        totalWithTax = totalPrice + taxAmount;
    }
}
