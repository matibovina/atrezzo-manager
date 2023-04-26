package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles roleName;
}
