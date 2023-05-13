package com.atrezzo.manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5172903370697566088L;
    private Long id;
    private String street;
    private String streetNumber;
    private String apartmentNumber;
    private String city;
    private String province;
    private String postalCode;
    private String country;

}
