package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleDTOTest {

    @Test
    @DisplayName("create role")
    void createRoleTest() {
        Long id = 1L;
        Roles roleName = Roles.ADMIN_ROLE;

        RoleDTO role = new RoleDTO(id, roleName);

        assertEquals(id, role.getId());
        assertEquals(roleName, role.getRoleName());
    }
}
