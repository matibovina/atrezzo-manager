package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOTest {



    @Test
    @DisplayName("create user")
    void createUserTest(){

        UserDTO user1 = new UserDTO();
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
