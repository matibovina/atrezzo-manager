package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserRoleTest {


    @Test
    @DisplayName("Add Role to User")
    void addRoleToUserTest() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("matibovina");
        user1.setEmail("matias@matias.com");
        user1.setPassword("123456");

        Role role = new Role();
        role.setId(1L);
        role.setRoleName(Roles.ADMIN_ROLE);

        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUser(user1);
        userRole.setRole(role);

        assertEquals(1L, userRole.getId());
        assertEquals(user1, userRole.getUser());
        assertEquals(role, userRole.getRole());
    }

}
