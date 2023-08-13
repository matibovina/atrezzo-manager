package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.RoleDTO;
import com.atrezzo.manager.application.service.RoleService;
import com.atrezzo.manager.presentation.controller.RoleController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.Currency;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO)
    throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(roleService.createRole(roleDTO), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<RoleDTO> findRoleById(@PathVariable Long id) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(roleService.findRoleById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAllRoles() throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) throws NoClassFoundException, CustomIllegalArgumentException {
        roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
