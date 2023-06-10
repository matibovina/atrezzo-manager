package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.RoleDTO;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface RoleService {

    //create Role

    RoleDTO createRole (RoleDTO roleDTO);

    //find role by id
    RoleDTO findRoleById(Long id);

    //find all roles
    List<RoleDTO> findAllRoles();

    //update role
    RoleDTO updateRole(Long id, RoleDTO roleDTO);

    //delete role by id
    void deleteRoleById(Long id);


}
