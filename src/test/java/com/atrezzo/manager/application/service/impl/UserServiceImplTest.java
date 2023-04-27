package com.atrezzo.manager.application.service.impl;


import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.model.UserRole;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.repository.RoleRepository;
import com.atrezzo.manager.domain.repository.UserRepository;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import com.atrezzo.manager.infrastructure.persistence.UserRoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserServiceImpl userService;

    UserEntity userEntity;

    User user;

    RoleEntity roleEntity;

    ModelMapper modelMapper = new ModelMapper();
    UserRole userRole;

    @BeforeEach
    void setUp() {

        userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setEmail("matias@matias.com");
        userEntity.setEnabled(true);
        userEntity.setPassword("12345");
        userEntity.setRoles(new ArrayList<>());
        userEntity.setCreationDate(null);

        user = new User();
        user.setId(1L);
        user.setEmail("pepe@pepe.com");
        user.setUsername("matibovina");
        user.setEnabled(true);
        user.setPassword("abcd");
        user.setRoles(new ArrayList<>());

        Role role = new Role();
        role.setId(1L);
        role.setRoleName(Roles.ADMIN_ROLE);


        userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);


        roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRoleName(Roles.ADMIN_ROLE);

    }

    @Test
    @DisplayName("Create new User")
    void createUserTest() {

        UserEntity userEntity1 = modelMapper.map(user, UserEntity.class);
        userEntity1.setCreationDate(LocalDateTime.now());

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity1);
        when(roleRepository.findByRoleName(roleEntity.getRoleName())).thenReturn(Optional.of(roleEntity));

        User newUser = userService.createUser(user, "ADMIN_ROLE");

        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(roleRepository, times(1)).findByRoleName(roleEntity.getRoleName());
        assertNotNull(newUser);
        assertEquals(userEntity1.getId(), newUser.getId());
        assertEquals(userEntity1.getEmail(), newUser.getEmail());
        assertEquals(userEntity1.getPassword(), newUser.getPassword());
        List<Roles> roleNames = newUser.getRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());
        assertTrue(roleNames.contains(Roles.ADMIN_ROLE));        assertTrue(roleNames.contains(Roles.ADMIN_ROLE));
    }


    @Test
    @DisplayName("Find user by id")
    void findUserByIdTest() throws ClassNotFoundException {

        Long id = 1L;

        user.getRoles().add(userRole);

        UserEntity userEntity1 = modelMapper.map(user, UserEntity.class);

        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(userEntity1));

        User foundUserEntity = userService.findById(id);

        assertNotNull(foundUserEntity);
        assertEquals(id, foundUserEntity.getId());
        assertEquals(userEntity1.getUsername(), foundUserEntity.getUsername());

    }

    @Test
    @DisplayName("Find user by username")
    void findUserByUserNameTest() {

        String username = "matibovina";

        userEntity = modelMapper.map(user, UserEntity.class);

        when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(userEntity));

        User foundUser = userService.findByUsername(username);

        assertNotNull(foundUser);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(username, foundUser.getUsername());

    }

    @Test
    @DisplayName("Find by email")
    void findByEmail () {
        String email = "pepe@pepe.com";
        userEntity = modelMapper.map(user, UserEntity.class);

        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(userEntity));


        User foundUser = userService.findByEmail(email);

        assertNotNull(foundUser);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(email, foundUser.getEmail());
    }


    //Find all

    //Update User

    //Add Role to User

    //Delete User

}
