package com.atrezzo.manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientAddressDTO {

    private ClientDTO clientDTO;

    private AddressDTO addressDTO;

}
