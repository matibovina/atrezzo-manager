package com.atrezzo.manager.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private Long id;

    private Client client;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String department;

    private String position;

    private String profilePicture;

}
