package com.atrezzo.manager.infrastucture.persistence;


import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.model.UserRole;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import com.atrezzo.manager.infrastructure.persistence.UserRoleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRoleEntityTest {



    @Test
    @DisplayName("Add Role to User")
    void addRoleToUserTest() {
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("matibovina");
        user1.setEmail("matias@matias.com");
        user1.setPassword("123456");

        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setRoleName(Roles.ADMIN_ROLE);

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setId(1L);
        userRole.setUser(user1);
        userRole.setRole(role);

        assertEquals(1L, userRole.getId());
        assertEquals(user1, userRole.getUser());
        assertEquals(role, userRole.getRole());
    }

}
