package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {


    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles roleName;

    @Serial
    private static final long serialVersionUID = 4833527155511237476L;
}
