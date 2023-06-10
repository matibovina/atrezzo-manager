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
public class ClientAddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8848835178511867451L;

    private ClientDTO clientDTO;
    private AddressDTO addressDTO;

}
