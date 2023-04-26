package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.RoleDTO;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface RoleService {

    //create Role

    RoleDTO createRole (RoleDTO roleDTO) throws IllegalArgumentException;

    //find role by id
    RoleDTO findRoleById(Long id) throws RoleNotFoundException, ClassNotFoundException;

    //find all roles
    List<RoleDTO> findAllRoles() throws RoleNotFoundException;

    //update role
    RoleDTO updateRole(Long id, RoleDTO roleDTO) throws IllegalArgumentException;

    //delete role by id
    void deleteRoleById(Long id) throws ClassNotFoundException;


}
