package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.RoleDTO;
import com.atrezzo.manager.application.service.RoleService;
import com.atrezzo.manager.presentation.controller.RoleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleControllerImpl implements RoleController {

    @Autowired
    private RoleService roleService;

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> findRoleById(@PathVariable Long id) throws RoleNotFoundException, ClassNotFoundException {
        RoleDTO roleDTO = roleService.findRoleById(id);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> findAllRoles() throws RoleNotFoundException {
        List<RoleDTO> roles = roleService.findAllRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) throws RoleNotFoundException, ClassNotFoundException {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id no puede ser nulo ni menos a cero");
        }

        roleService.deleteRoleById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
