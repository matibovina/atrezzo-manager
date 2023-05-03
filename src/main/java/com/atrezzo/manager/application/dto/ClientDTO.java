package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.Address;
import com.atrezzo.manager.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {


    private Long id;

        private String companyName;

        private String legalName;

        private String cuitNumber;

        //private TaxCondition taxCondition;

        private String email;

        private String phone;

        private Address address;

        private User user;

        private String profilePicture;

        //private List<Contact> contacts;

        //private List<Quotes> quotes;

        //private List<Invoice> invoices;

        //private List<Event> events;


    @Serial
    private static final long serialVersionUID = 5061111056201736831L;
}


