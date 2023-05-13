package com.atrezzo.manager.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long id;
    private String street;
    private String streetNumber;
    private String apartmentNumber;
    private String city;
    private String province;
    private String postalCode;
    private String country;

}
