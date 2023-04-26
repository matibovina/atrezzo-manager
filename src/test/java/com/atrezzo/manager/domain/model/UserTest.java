package com.atrezzo.manager.domain.model;

import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {


    @Test
    @DisplayName("create user")
    void createUserTest(){

        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleName(Roles.ADMIN_ROLE);

        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("matibovina");
        user1.setEmail("matias@matias.com");
        user1.setPassword("123456");

        assertEquals(1, user1.getId());
        assertEquals("matibovina", user1.getUsername());
        assertEquals("matias@matias.com", user1.getEmail());
        assertEquals("123456", user1.getPassword());
    }


}
