package com.atrezzo.manager.application.service.impl;


import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.model.UserRole;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.repository.RoleRepository;
import com.atrezzo.manager.domain.repository.UserRepository;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        role.setRoleName(Roles.ROLE_ADMIN);


        userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);


        roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRoleName(Roles.ROLE_ADMIN);


    //    when(bCryptPasswordEncoder.encode(anyString())).thenAnswer(invocation -> "encoded_" + invocation.getArgument(0));

    }

    @Test
    @DisplayName("Create new User")
    void createUserTest() {

        UserEntity userEntity1 = modelMapper.map(user, UserEntity.class);
        userEntity1.setCreationDate(LocalDateTime.now());

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity1);
        when(roleRepository.findByRoleName(roleEntity.getRoleName())).thenReturn(Optional.of(roleEntity));

        User newUser = userService.createUser(user, "ROLE_ADMIN");

        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(roleRepository, times(1)).findByRoleName(roleEntity.getRoleName());
        assertNotNull(newUser);
        assertEquals(userEntity1.getId(), newUser.getId());
        assertEquals(userEntity1.getEmail(), newUser.getEmail());
        assertTrue("abcd".equals(newUser.getPassword()));

        // assertEquals(userEntity1.getPassword(), newUser.getPassword());
        List<Roles> roleNames = newUser.getRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());
        assertTrue(roleNames.contains(Roles.ROLE_ADMIN));
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
    void findByEmailTest () {
        String email = "pepe@pepe.com";
        userEntity = modelMapper.map(user, UserEntity.class);

        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(userEntity));


        User foundUser = userService.findByEmail(email);

        assertNotNull(foundUser);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(email, foundUser.getEmail());
    }


    //Find all
    @Test
    @DisplayName("Find all Users")
    void findAllUsersTest() {

        UserEntity testUser = new UserEntity(null, "testuser", "test@example.com", "password", new ArrayList<>(), true, null);
        UserEntity testUser1 = new UserEntity(null, "testuser", "test@example.com", "password", new ArrayList<>(), true, null);

        List<UserEntity> userEntities = Arrays.asList(testUser, testUser1);

        when(userRepository.findAll()).thenReturn(userEntities);

        List<User> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertEquals(testUser.getId(), foundUsers.get(0).getId());
        assertEquals(testUser.getEmail(), foundUsers.get(0).getEmail());
        assertEquals(testUser1.getId(), foundUsers.get(1).getId());
        assertEquals(testUser1.getEmail(), foundUsers.get(1).getEmail());
    }

    //Update User
    @Test
    @DisplayName("Change password")
    void changeUserPasswordTest() {

        String username = "matibovina";
        String oldPassword = "abc123";
        String newPassword = "123abc";
        UserEntity userEntity1 = modelMapper.map(user, UserEntity.class);
        userEntity1.setPassword(oldPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity1));

        User updatedUser = userService.changePassword(username, oldPassword, newPassword);

        assertNotNull(updatedUser);
        assertEquals(username, updatedUser.getUsername());
        assertTrue(newPassword.equals(updatedUser.getPassword()));

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(userEntity1);
    }

    //Update User

    @Test
    @DisplayName("Update User")
    void updateUserTest() throws ClassNotFoundException {

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        userEntity1.setUsername("abcd");
        userEntity1.setEmail("a@asd.com");
        userEntity1.setPassword("password1");
        userEntity1.setRoles(new ArrayList<>());

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(1L);
        userEntity2.setUsername("abcd");
        userEntity2.setEmail("aasdd@asd.com");
        userEntity2.setPassword("password1");
        userEntity2.setRoles(new ArrayList<>());

        when(userRepository.existsById(userEntity1.getId())).thenReturn(true);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity2);

        User updatedUser = userService.updateUser(modelMapper.map(userEntity1, User.class));

        assertNotNull(updatedUser);
        assertNotNull(updatedUser);
        assertEquals(userEntity1.getId(), updatedUser.getId(), "The updated user should have the same ID as the original user.");
        assertEquals(userEntity1.getUsername(), updatedUser.getUsername(), "The updated user should have a different username than the original user.");
        assertNotEquals(userEntity1.getEmail(), updatedUser.getEmail(), "The updated user should have a different email than the original user.");
        assertEquals(userEntity1.getPassword(), updatedUser.getPassword(), "The updated user should have the same password than the original user.");



    }



    //Delete User
    @Test
    void deleteUserTest() {

        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);
        doNothing().when(userRepository).deleteById(id);

        assertDoesNotThrow(() -> userService.deleteUserById(id));
        verify(userRepository, times(1)).deleteById(id);

    }

}
