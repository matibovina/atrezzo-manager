package com.atrezzo.manager.infrastucture.persistence;

import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEntityTest {


    @Test
    @DisplayName("create user")
    void createUserTest(){

        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setUsername("matibovina");
        userEntity.setEmail("matias@matias.com");
        userEntity.setPassword("123456");

        assertEquals(1, userEntity.getId());
        assertEquals("matibovina", userEntity.getUsername());
        assertEquals("matias@matias.com", userEntity.getEmail());
        assertEquals("123456", userEntity.getPassword());
    }

}
