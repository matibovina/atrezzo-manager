package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    private Long id;
    private String title;
    private Client client;
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
