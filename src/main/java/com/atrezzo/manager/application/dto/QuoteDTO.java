package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.enums.QuoteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9110298148184642551L;

    private Long id;
    private String title;
    private ClientDTO client;
    private String location;
    private String description;
    private Double totalPrice;
    private Double taxPercentage;
    private Double taxAmount;
    private Double totalWithTax;
    private LocalDateTime createdAt;
    private LocalDateTime statusChangedAt;
    private QuoteStatus status;
    private List<ContactDTO> contacts;
    //private Invoice invoice;
    //private List<ExtraItem> extraItems;
    private EventDTO event;
    private List<EventSessionDTO> eventSessions;

}
