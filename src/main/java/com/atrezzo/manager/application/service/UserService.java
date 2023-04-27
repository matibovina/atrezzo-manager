package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.domain.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user, String role);

    User findById(Long id) throws ClassNotFoundException;

    User findByUsername(String username);

    User findByEmail(String email);
}
