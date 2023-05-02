package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ChangePasswordRequest;
import com.atrezzo.manager.application.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    ResponseEntity<UserDTO> createUser(UserDTO userDTO );

    ResponseEntity<UserDTO> findUserById(Long id);

    ResponseEntity<List<UserDTO>> findAllUsers();

    ResponseEntity<?> updateUser(UserDTO userDTO) throws ClassNotFoundException;

    ResponseEntity<?> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest);

    ResponseEntity<?> deleteUserById(Long id);

    ResponseEntity<?> findUserByRole(String roleName);
}
