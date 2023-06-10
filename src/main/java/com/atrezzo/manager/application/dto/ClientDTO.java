package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.enums.TaxCondition;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {


        private Long id;
        private String companyName;
        private String legalName;
        private String cuitNumber;
        @Enumerated(EnumType.STRING)
        private TaxCondition taxCondition;
        private String email;
        private String phone;
        private AddressDTO address;
        private UserDTO user;
        private String profilePicture;
        private Boolean enabled;

    @Serial
    private static final long serialVersionUID = 5061111056201736831L;
}


