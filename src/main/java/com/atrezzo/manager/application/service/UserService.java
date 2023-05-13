package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.domain.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user, String role);

    public User updateUser(User user) throws ClassNotFoundException;

    User findById(Long id) throws ClassNotFoundException;

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

    User changePassword(String username, String oldPassword, String newPassword);

    void deleteUserById(Long id);

    List<User> findAllUsersByRole(String roleName);
}
