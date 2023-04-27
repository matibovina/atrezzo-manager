package com.atrezzo.manager.domain.repository;


import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleRepositoryTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleRepositoryTest roleRepositoryTest;

    private RoleEntity roleEntity;


    @BeforeEach
    void setUp() {
        roleEntity = new RoleEntity(1L, Roles.WORKER_ROLE);
    }

    @Test
    @DisplayName("Encontrar role por nombre")
    void findByRoleNameTest() {

        Roles roleName = Roles.WORKER_ROLE;

        when(roleRepository.findByRoleName(roleName)).thenReturn(Optional.ofNullable(roleEntity));

        Optional<RoleEntity> result = roleRepository.findByRoleName(roleName);

        assertEquals(roleName, result.get().getRoleName());
        assertEquals(1L, result.get().getId());
    }

    @Test
    @DisplayName("create role in repository")
    public void createRoleTest() {

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(Roles.ADMIN_ROLE);

        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);

        RoleEntity savedRole = roleRepository.save(roleEntity);

        assertEquals(Roles.ADMIN_ROLE, savedRole.getRoleName());
    }

    @Test
    @DisplayName("Find By Id in repository")
    public void findRoleByIdTest() {
        RoleEntity roleEntity = new RoleEntity(1L, Roles.CLIENT_ROLE);

        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));

        Optional<RoleEntity> foundRoleEntity = roleRepository.findById(1L);

        assertTrue(foundRoleEntity.isPresent());
        assertEquals(Roles.CLIENT_ROLE, foundRoleEntity.get().getRoleName());
        assertEquals(1L, foundRoleEntity.get().getId());

    }

    @Test
    @DisplayName("Delete By Id in repository")
    public void deleteByIdTest() {

        RoleEntity roleEntity = new RoleEntity(1L, Roles.WORKER_ROLE);

        doNothing().when(roleRepository).deleteById(1L);

        roleRepository.deleteById(1L);

        verify(roleRepository, times(1)).deleteById(1L);

    }
}
