package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRoleDTOTest {


    @Test
    @DisplayName("Add Role to User")
    void addRoleToUserTest() {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setUsername("matibovina");
        user1.setEmail("matias@matias.com");
        user1.setPassword("123456");

        RoleDTO role = new RoleDTO();
        role.setId(1L);
        role.setRoleName(Roles.ROLE_ADMIN);

        UserRoleDTO userRole = new UserRoleDTO();
        userRole.setId(1L);
        userRole.setUser(user1);
        userRole.setRole(role);

        assertEquals(1L, userRole.getId());
        assertEquals(user1, userRole.getUser());
        assertEquals(role, userRole.getRole());
    }

}
