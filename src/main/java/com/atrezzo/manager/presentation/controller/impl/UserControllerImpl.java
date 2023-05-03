package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.util.ChangePasswordRequest;
import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.application.service.UserService;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.presentation.controller.UserController;
import com.atrezzo.manager.presentation.responses.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserControllerImpl.class);


    @Override
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            User user = modelMapper.map(userDTO, User.class);
            User createdUser = userService.createUser(user, userDTO.getRoleName());
            UserDTO createdUserDTO = modelMapper.map(createdUser, UserDTO.class);
            System.out.println(log.atError());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
        } catch (IllegalArgumentException e) {
            log.error("Error al crear el usuario: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (RuntimeException e) {
            log.error("Error al crear el usuario: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            log.error("Error al crear el usuario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {

        try {
            User user = userService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(user, UserDTO.class));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAllUsers() {

        List<User> users= userService.findAll();

        List<UserDTO> usersDTO = users.stream().map(
                u-> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @Override
    @PutMapping( value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) throws ClassNotFoundException {

        try {
            if(userDTO == null || userDTO.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or user ID cannot be null.");
            }
            User user = modelMapper.map(userDTO, User.class);
            User updatedUser =  updatedUser = userService.updateUser(user);

            UserDTO updatedUserDTO = modelMapper.map(user, UserDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user.");
        }
    }

    @Override
    @PostMapping("/user/pass")
    public ResponseEntity<?> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {

        try {
            User user = userService.changePassword(changePasswordRequest.getUsername(), changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());

            UserDTO updatedUserDTO = modelMapper.map(user, UserDTO.class);

            return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    @Override
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {

        try {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User with ID " + id + " was deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
        }
    }

    @Override
    @GetMapping(value = "/byRole/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserByRole(@PathVariable String roleName) {

        List<User> users = userService.findAllUsersByRole(roleName);

        List<UserDTO> usersDTO = users.stream().map(
                u-> modelMapper.map(u, UserDTO.class)
        ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }
}
