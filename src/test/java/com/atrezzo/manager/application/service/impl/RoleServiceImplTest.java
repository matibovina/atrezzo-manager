package com.atrezzo.manager.application.service.impl;


import com.atrezzo.manager.application.dto.RoleDTO;
import com.atrezzo.manager.application.exceptions.NoRolesFoundException;
import com.atrezzo.manager.domain.model.Role;
import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.repository.RoleRepository;
import com.atrezzo.manager.infrastructure.persistence.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
   private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private ModelMapper modelMapper = new ModelMapper();

    private Role role;
    private RoleEntity roleEntity;
    private RoleDTO roleDTO;

    @BeforeEach
    public void setUp() {
        role = new Role();
        role.setRoleName(Roles.ROLE_WORKER);
        roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRoleName(Roles.ROLE_WORKER);
        roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setRoleName(Roles.ROLE_WORKER);
    }

    @Test
    @DisplayName("Create Role")
    void createRoleTest() {
//        Roles enumRole = Roles.valueOf(roleDTO.getRoleName());
//        roleEntity.setRoleName(enumRole);
        modelMapper.map(roleDTO, RoleEntity.class);

        when(roleRepository.save(roleEntity)).thenReturn(roleEntity);

        RoleDTO createdRole = roleService.createRole(modelMapper.map(roleEntity, RoleDTO.class));
        System.out.println(createdRole.getId());

       assertNotNull(createdRole);
       assertEquals(1L, createdRole.getId());
       assertEquals(Roles.ROLE_WORKER, createdRole.getRoleName());

    }

    @Test
    @DisplayName("Failed crate Role")
    void createRoleFailsTest() {

        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setRoleName(null);
        assertThrows(IllegalArgumentException.class, ()-> {
            roleService.createRole(roleDTO1);
        });
    }

    @Test
    @DisplayName("Find By Id")
    void findByIdTest() throws NoRolesFoundException {
        Long id = 1L;

        when(roleRepository.findById(id)).thenReturn(Optional.of(roleEntity));

        RoleDTO foundRoleDto = roleService.findRoleById(id);

        assertNotNull(foundRoleDto);
        assertEquals(Roles.ROLE_WORKER, foundRoleDto.getRoleName());
    }

    @Test
    @DisplayName("Failed find by Id")
    void findRoleByIdFailsTest() {
        Long id = 1L;

        when(roleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoRolesFoundException.class, () -> roleService.findRoleById(id));
    }


    @Test
    @DisplayName("Get All Roles")
    void getAllRolesTest() throws NoRolesFoundException {

        RoleEntity roleEntity1 = new RoleEntity(2L, Roles.ROLE_ADMIN);
        RoleEntity roleEntity2 = new RoleEntity(3L, Roles.ROLE_CLIENT);



        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.addAll(Arrays.asList(roleEntity1, roleEntity2));

        when(roleRepository.findAll()).thenReturn(roleEntities);

        List<RoleDTO> roleDTOS = roleService.findAllRoles();

        assertNotNull(roleDTOS);
        assertEquals(roleEntity1.getId(), roleDTOS.get(0).getId());
        assertEquals(roleEntity2.getId(), roleDTOS.get(1).getId());
        assertEquals(roleEntity1.getRoleName(), roleDTOS.get(0).getRoleName());
        assertEquals(roleEntity2.getRoleName(), roleDTOS.get(1).getRoleName());


    }


    @Test
    @DisplayName("Failed get all Roles")
    void getAllRolesFailsTest() {

        List<RoleEntity> roleEntities = new ArrayList<>();

        when(roleRepository.findAll()).thenReturn(roleEntities);

        assertThrows(NoRolesFoundException.class, () -> roleService.findAllRoles());

    }


    @Test
    @DisplayName("Update Role")
    void updateRoleTest() {
        Long id = 1L;

        RoleDTO updateRoleDto = new RoleDTO();
        updateRoleDto.setId(1L);
        updateRoleDto.setRoleName(Roles.ROLE_ADMIN);



        when(roleRepository.findById(id)).thenReturn(Optional.ofNullable(roleEntity));

        RoleDTO resultDTO = roleService.updateRole(id, updateRoleDto);

        assertNotNull(resultDTO);
        assertEquals(Roles.ROLE_ADMIN, resultDTO.getRoleName());
        assertEquals(id, resultDTO.getId());

    }

    @Test
    @DisplayName("Failed Update Role")
    void updateRoleFailsTest() {
        Long id = 1L;
        RoleDTO updateRole = new RoleDTO();
        updateRole.setId(1L);
        updateRole.setRoleName(Roles.ROLE_ADMIN);

        when(roleRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoRolesFoundException.class, () -> roleService.updateRole(id, updateRole));
    }


    @Test
    @DisplayName("Delete Role")
    void deleteRoleTest() {
        Long id = 1L;

        when(roleRepository.existsById(id)).thenReturn(true);

        doNothing().when(roleRepository).deleteById(id);
        assertDoesNotThrow(() -> roleService.deleteRoleById(id));
        verify(roleRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Failed Delete Role")
    void deleteRoleFailsTest() {
        Long id = 1L;

        when(roleRepository.existsById(id)).thenReturn(false);

        assertThrows(NoRolesFoundException.class, ()-> roleService.deleteRoleById(id));
    }

}
