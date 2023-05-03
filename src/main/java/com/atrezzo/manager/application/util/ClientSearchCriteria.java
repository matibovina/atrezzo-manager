package com.atrezzo.manager.application.util;

import com.atrezzo.manager.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearchCriteria {

    private User user;
    private String cuit;
    private String companyName;
    private String legalName;

}
