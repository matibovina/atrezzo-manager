package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user, String role);

    public UserDTO updateUser(UserDTO user) throws NoClassFoundException;

    UserDTO findById(Long id) throws NoClassFoundException;

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();

    UserDTO changePassword(String username, String oldPassword, String newPassword);

    void deleteUserById(Long id);

    List<UserDTO> findAllUsersByRole(String roleName);
}
