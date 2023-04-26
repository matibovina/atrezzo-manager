package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleTest {

    @Test
    @DisplayName("create role")
    void createRoleTest() {
        Long id = 1L;
        Roles roleName = Roles.ADMIN_ROLE;

        Role role = new Role(id, roleName);

        assertEquals(id, role.getId());
        assertEquals(roleName, role.getRoleName());
    }
}
