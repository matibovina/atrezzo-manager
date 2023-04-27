package com.atrezzo.manager.infrastucture.persistence;

import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleEntityTest {

    @Test
    @DisplayName("create role")
    void createRoleTest() {
        Long id = 1L;
        Roles roleName = Roles.ADMIN_ROLE;

        RoleEntity role = new RoleEntity(id, roleName);

        assertEquals(id, role.getId());
        assertEquals(roleName, role.getRoleName());
    }
}
