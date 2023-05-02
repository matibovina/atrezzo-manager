package com.atrezzo.manager.presentation.controller.impl;


import com.atrezzo.manager.application.dto.RoleDTO;
import com.atrezzo.manager.application.exceptions.NoRolesFoundException;
import com.atrezzo.manager.application.service.RoleService;
import com.atrezzo.manager.domain.model.enums.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.management.relation.RoleNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleControllerImpl roleController;

    private RoleDTO roleDTO;

    @BeforeEach
    public void setUp(){
        roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setRoleName(Roles.ROLE_ADMIN);
    }

    @Test
    @DisplayName("Create Role in Controller")
    void createRoleTest() {
        when(roleService.createRole(roleDTO)).thenReturn(roleDTO);

        ResponseEntity<RoleDTO> response = roleController.createRole(roleDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(roleDTO, response.getBody());
    }


    @Test
    @DisplayName("Find Role By Id")
    void findRoleByIdTest() throws RoleNotFoundException, ClassNotFoundException {

        Long id = 1L;

        when(roleService.findRoleById(id)).thenReturn(roleDTO);

        ResponseEntity<RoleDTO> response = roleController.findRoleById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roleDTO, response.getBody());


    }

    @Test
    @DisplayName("Find all Roles")
    void findAllRolesTest() throws RoleNotFoundException {

        RoleDTO roleDTO1 = new RoleDTO(2L, Roles.ROLE_CLIENT);
        RoleDTO roleDTO2 = new RoleDTO(3L, Roles.ROLE_WORKER);

        List<RoleDTO> roleDTOS = Arrays.asList(roleDTO1, roleDTO2);


        when(roleService.findAllRoles()).thenReturn(roleDTOS);
        ResponseEntity<List<RoleDTO>> response = roleController.findAllRoles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(roleDTOS, response.getBody());
        assertEquals(roleDTO1.getId(), roleDTOS.get(0).getId());

    }

    @Test
    @DisplayName("Delete Role")
    void deleteRoleTest() throws ClassNotFoundException, RoleNotFoundException {
        Long id = 1L;

        doNothing().when(roleService).deleteRoleById(id);

        assertDoesNotThrow(() -> roleController.deleteById(id));
        verify(roleService, times(1)).deleteRoleById(id);


    }
    @Test
    @DisplayName("Delete Role with null ID")
    void deleteRoleWithNullIdTest() throws ClassNotFoundException {
        assertThrows(IllegalArgumentException.class, () -> roleController.deleteById(null));
        verify(roleService, never()).deleteRoleById(null);
    }

    @Test
    @DisplayName("Delete Role with ID <= 0")
    void deleteRoleWithInvalidIdTest() throws ClassNotFoundException {
        Long id = 0L;
        assertThrows(IllegalArgumentException.class, () -> roleController.deleteById(id));
        verify(roleService, never()).deleteRoleById(id);
    }

    @Test
    @DisplayName("Delete non-existent Role")
    void deleteNonExistentRoleTest() throws ClassNotFoundException {
        Long id = 1L;
        doThrow(NoRolesFoundException.class).when(roleService).deleteRoleById(id);

        assertThrows(NoRolesFoundException.class, () -> roleController.deleteById(id));
        verify(roleService, times(1)).deleteRoleById(id);
    }

}
