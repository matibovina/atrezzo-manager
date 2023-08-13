package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.util.ChangePasswordRequest;
import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    ResponseEntity<UserDTO> createUser(UserDTO userDTO );

    ResponseEntity<UserDTO> findUserById(Long id);

    ResponseEntity<List<UserDTO>> findAllUsers();

    ResponseEntity<UserDTO> updateUser(UserDTO userDTO) throws NoClassFoundException;

    ResponseEntity<UserDTO> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest);

    ResponseEntity<?> deleteUserById(Long id);

    ResponseEntity<List<UserDTO>> findUserByRole(Roles roleName);
}
