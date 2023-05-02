package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ChangePasswordRequest;
import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.application.service.impl.UserServiceImpl;
import com.atrezzo.manager.application.service.impl.UserServiceImplTest;
import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.model.UserRole;
import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserControllerImpl userController;

    ModelMapper modelMapper;

    UserDTO userDTO;

    @BeforeEach
    void setup() {

        modelMapper = new ModelMapper();

        userDTO = new UserDTO();

        userDTO.setId(null);
        userDTO.setEmail("matias@matias.com");
        userDTO.setEnabled(true);
        userDTO.setPassword("12345");
        userDTO.setRoles(new ArrayList<>());
        userDTO.setCreationDate(null);
        userDTO.setRoleName("ROLE_CLIENT");
    }
    
    @Test
    void createUserTest() {

        User user = modelMapper.map(userDTO, User.class);

        UserRole anotherUserRole = new UserRole();
        anotherUserRole.setRole(new Role(1L, Roles.valueOf(userDTO.getRoleName())));
        user.getRoles().add(anotherUserRole);

        when(userService.createUser(ArgumentMatchers.any(User.class), ArgumentMatchers.eq(userDTO.getRoleName()))).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        assertNotNull(response);
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getRoles().get(0).getRole().getRoleName(), response.getBody().getRoles().get(0).getRole().getRoleName());

    }

    //FindUserById

    @Test
    @DisplayName("Find User by ID")
    void findUserByIdTest() throws ClassNotFoundException {

        Long id = 1L;
        userDTO.setId(1L);
        when(userService.findById(id)).thenReturn(modelMapper.map(userDTO, User.class));

        ResponseEntity<UserDTO> response = userController.findUserById(id);

        assertNotNull(response);
        assertEquals(id, response.getBody().getId());
        assertEquals(userDTO.getEmail(), response.getBody().getEmail());



    }


    //FindAllUsers

    @Test
    @DisplayName("Find all Users")
    void findAllUsersTest() {

        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("abcd");
        user1.setEmail("a@asd.com");
        user1.setRoles(new ArrayList<>());

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("123456");
        user2.setEmail("aasdd@asd.com");
        user2.setRoles(new ArrayList<>());

        List<User> users= new ArrayList<>();
        users = Arrays.asList(user1, user2);


        when(userService.findAll()).thenReturn(
                users.stream().map(u -> modelMapper.map(u, User.class)).collect(Collectors.toList())
        );

        ResponseEntity<List<UserDTO>> response= userController.findAllUsers();

        assertNotNull(response);
        assertEquals(user1.getId(), response.getBody().get(0).getId());
        assertEquals(user2.getId(), response.getBody().get(1).getId());
        assertEquals(user1.getEmail(), response.getBody().get(0).getEmail());
        assertEquals(user2.getEmail(), response.getBody().get(1).getEmail());
    }


    //UpdateUser

    @Test
    @DisplayName("Update User - Success")
    void updateUserSuccessTest() throws ClassNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUsername");
        userDTO.setEmail("updatedEmail@example.com");
        userDTO.setPassword("updatedPassword");

        User user = modelMapper.map(userDTO, User.class);

        when(userService.updateUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserDTO);
        assertEquals(userDTO.getUsername(), ((UserDTO) response.getBody()).getUsername());
        assertEquals(userDTO.getEmail(), ((UserDTO) response.getBody()).getEmail());
    }

    @Test
    @DisplayName("Update User - Null User")
    void updateUserNullUserTest() throws ClassNotFoundException {
        ResponseEntity<?> response = userController.updateUser(null);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User or user ID cannot be null.", response.getBody());
    }

    @Test
    @DisplayName("Update User - Null User ID")
    void updateUserNullUserIdTest() throws ClassNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("updatedUsername");
        userDTO.setEmail("updatedEmail@example.com");
        userDTO.setPassword("updatedPassword");

        ResponseEntity<?> response = userController.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User or user ID cannot be null.", response.getBody());
    }

    @Test
    @DisplayName("Update User - User Not Found")
    void updateUserNotFoundTest() throws ClassNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUsername");
        userDTO.setEmail("updatedEmail@example.com");
        userDTO.setPassword("updatedPassword");

        User user = modelMapper.map(userDTO, User.class);

        when(userService.updateUser(user)).thenThrow(ClassNotFoundException.class);

        ResponseEntity<?> response = userController.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found.", response.getBody());
    }

    @Test
    @DisplayName("Update User - IllegalArgumentException")
    void updateUserIllegalArgumentExceptionTest() throws ClassNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUsername");
        userDTO.setEmail("updatedEmail@example.com");
        userDTO.setPassword("updatedPassword");

        User user = modelMapper.map(userDTO, User.class);

        when(userService.updateUser(user)).thenThrow(new IllegalArgumentException("Invalid input."));

        ResponseEntity<?> response = userController.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input.", response.getBody());
    }

    @Test
    @DisplayName("Update User - Generic Exception")
    void updateUserGenericExceptionTest() throws ClassNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUsername");
        userDTO.setEmail("updatedEmail@example.com");
        userDTO.setPassword("updatedPassword");

        User user = modelMapper.map(userDTO, User.class);

        when(userService.updateUser(user)).thenThrow(new RuntimeException("Unexpected error."));

        ResponseEntity<?> response = userController.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while updating the user.", response.getBody());
    }



    //ChangePassword
    @Test
    @DisplayName("Change password - Success")
    void changeUserPasswordTest() {

        UserDTO updatedUserDTO = userDTO;
        updatedUserDTO.setPassword("abc123abc");

        User user = modelMapper.map(updatedUserDTO, User.class);

        when(userService.changePassword(user.getUsername(), userDTO.getPassword(), user.getPassword())).thenReturn(user);
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setUsername(user.getUsername());
        changePasswordRequest.setOldPassword(userDTO.getPassword());
        changePasswordRequest.setNewPassword(user.getPassword());

        ResponseEntity<?> response = userController.changeUserPassword(changePasswordRequest);

        assertNotNull(response);
        assertEquals(user.getPassword(), ((UserDTO)response.getBody()).getPassword());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


    //DeleteUser

    @Test
    @DisplayName("Delete User - Success")
    void deleteUserTest() {

        Long id = 1L;

        doNothing().when(userService).deleteUserById(id);

        ResponseEntity<?> response = userController.deleteUserById(id);

        verify(userService, times(1)).deleteUserById(id);



    }

}
