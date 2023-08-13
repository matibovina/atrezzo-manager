package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface RoleController {

    ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO);

    ResponseEntity<RoleDTO> findRoleById(Long id) throws RoleNotFoundException, ClassNotFoundException;

    ResponseEntity<List<RoleDTO>> findAllRoles();

    ResponseEntity<?> deleteById(Long id);


}
