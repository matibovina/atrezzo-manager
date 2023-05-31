package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.Contact;
import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO {

    private Long id;
    private String title;
    private ClientDTO client;
    private String location;
    private String description;
    private Double totalPrice;
    private Double taxPercentage;
    private Double taxAmount;
    private Double totalWithTax;

    @Enumerated(EnumType.STRING)
    private QuoteStatus status;
    private List<Contact> contacts;
    //private Invoice invoice;
    //private List<ExtraItem> extraItems;
    //private Event event;
    //private List<EventSession> eventSessions;



}
