package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.util.ChangePasswordRequest;
import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.application.service.UserService;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.presentation.controller.UserController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import com.atrezzo.manager.presentation.responses.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserControllerImpl.class);


    @Override
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
            throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(userService.createUser(userDTO, userDTO.getRoleName()), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id)
            throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Override
    @PutMapping("/user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @Override
    @PostMapping("/user/pass")
    public ResponseEntity<UserDTO> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return new ResponseEntity<>(userService.changePassword(changePasswordRequest.getUsername(),
                changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword()),
                HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws NoClassFoundException, CustomIllegalArgumentException {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping("/byRole/{roleName}")
    public ResponseEntity<List<UserDTO>> findUserByRole(@PathVariable Roles roleName) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(userService.findAllUsersByRole(String.valueOf(roleName)), HttpStatus.OK);
    }
}
