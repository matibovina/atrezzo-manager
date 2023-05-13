package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.TaxCondition;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    private Long id;
    private String firstName;
    private String lastName;
    private String cuit;
    private Address address;
    private TaxCondition taxCondition;
    private String email;
    private String phone;
    private UserEntity user;
    // private List<Service> services;
    // private List<Event> events;
    // private List<SessionService> sessionServices;
    // private List<Invoice> invoices;
    private Date birthDate;

    private String profilePicture;
}
