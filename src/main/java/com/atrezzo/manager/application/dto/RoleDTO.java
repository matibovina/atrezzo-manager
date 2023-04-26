package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.enums.Roles;
import lombok.Data;

@Data
public class RoleDTO {

    Long id;
    Roles roleName;

}
