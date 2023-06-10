package com.atrezzo.manager.application.util;

import com.atrezzo.manager.application.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearchCriteria {

    private UserDTO user;
    private String cuit;
    private String companyName;
    private String legalName;

}
