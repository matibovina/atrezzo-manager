package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.application.service.UserService;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.repository.RoleRepository;
import com.atrezzo.manager.domain.repository.UserRepository;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import com.atrezzo.manager.infrastructure.persistence.UserRoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private ModelMapper modelMapper = new ModelMapper();


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

        System.out.println(userEntity.getRoles().get(0).getRole().getRoleName());

        try {
            userRepository.save(userEntity);
            System.out.println(userEntity.getCreationDate());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return modelMapper.map(userEntity, User.class);
    }

    public User findById(Long id) throws ClassNotFoundException {

        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Id can't be null or less than 0");
        }

        UserEntity foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException("User not found"));

        return modelMapper.map(foundUser, User.class);


    }

    @Override
    public User findByUsername(String username) {

        if(username == null || username.isEmpty() ) {
            throw new IllegalArgumentException("Username can't be null or empty");
        }

        UserEntity foundUser = userRepository.findByUsername(username).orElseThrow(
                () -> new ClassCastException("User not found")
        );

        return modelMapper.map(foundUser, User.class);
    }

    @Override
    public User findByEmail(String email) {

        if(email == null || email.isEmpty() ) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }

        UserEntity foundUser = userRepository.findByEmail(email).orElseThrow(
                () -> new ClassCastException("User not found")
        );

        return modelMapper.map(foundUser, User.class);
    }


}
