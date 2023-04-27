package com.atrezzo.manager.domain.model;

import lombok.Data;

@Data
public class UserRole {

    private Long id;

    private User user;

    private Role role;
}
