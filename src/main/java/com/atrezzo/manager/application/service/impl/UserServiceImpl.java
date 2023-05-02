package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.exceptions.NoRolesFoundException;
import com.atrezzo.manager.application.service.UserService;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.repository.RoleRepository;
import com.atrezzo.manager.domain.repository.UserRepository;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import com.atrezzo.manager.infrastructure.persistence.UserRoleEntity;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private ModelMapper modelMapper = new ModelMapper();


    public UserServiceImpl( UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User createUser(User user, String role) {

        if (user == null || user.getEmail() == null || user.getUsername() == null ||
                user.getPassword() == null) {
            throw new IllegalArgumentException("User cant be null or undefined");
        }

        userRepository.findByUsername(user.getUsername()).ifPresent(
                existingUser -> {
                    throw new IllegalArgumentException("The username already exists.");
                });

        userRepository.findByEmail(user.getEmail()).ifPresent(
                existingUser -> {
                    throw new IllegalArgumentException("The email alrady exists.");
                });

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        RoleEntity roleEntity = roleRepository.findByRoleName(Roles.valueOf(role))
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(roleEntity);
        userRoleEntity.setUser(userEntity);

        userEntity.getRoles().add(userRoleEntity);

        try {
            userRepository.save(userEntity);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User updateUser(User user) throws ClassNotFoundException {

        if (user == null || user.getEmail() == null || user.getUsername() == null ||
                user.getPassword() == null) {
            throw new IllegalArgumentException("User cant be null or undefined");
        }

       if(!userRepository.existsById(user.getId())) {
           throw new IllegalArgumentException("User doesn't exist");
       }

       UserEntity updatedUser = userRepository.save(modelMapper.map(user, UserEntity.class));

        return modelMapper.map(updatedUser, User.class);
    }


    public User findById(Long id) throws ClassNotFoundException {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id can't be null or less than 0");
        }

        UserEntity foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException("User not found"));

        return modelMapper.map(foundUser, User.class);


    }

    @Override
    public User findByUsername(String username) {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username can't be null or empty");
        }

        UserEntity foundUser = userRepository.findByUsername(username).orElseThrow(
                () -> new ClassCastException("User not found")
        );

        return modelMapper.map(foundUser, User.class);
    }

    @Override
    public User findByEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }

        UserEntity foundUser = userRepository.findByEmail(email).orElseThrow(
                () -> new ClassCastException("User not found")
        );

        return modelMapper.map(foundUser, User.class);
    }

    @Override
    public List<User> findAll() {

        List<UserEntity> foundUsers = userRepository.findAll();

        return foundUsers.stream().map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found.")
        );

        if (!oldPassword.equals(userEntity.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        userEntity.setPassword(newPassword);
        userRepository.save(userEntity);

        return modelMapper.map(userEntity, User.class);
    }

    public void deleteUserById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id no puede ser nulo o menor o igual a 0");
        }
        if (!userRepository.existsById(id)) {
            throw new NoRolesFoundException("El rol con " + id + " no existe.");
        }

        userRepository.deleteById(id);

    }

    @Override
    public List<User> findAllUsersByRole(String roleName) {
            List<Roles> roles = Collections.singletonList(Roles.valueOf(roleName));
            List<UserEntity> userEntities = userRepository.findAllByRoleNames(roles);
            return userEntities.stream()
                    .map(u -> modelMapper.map(u, User.class))
                    .collect(Collectors.toList());
        }

}
