package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.RoleDTO;
import com.atrezzo.manager.application.exceptions.NoRolesFoundException;
import com.atrezzo.manager.application.service.RoleService;
import com.atrezzo.manager.domain.repository.RoleRepository;
import com.atrezzo.manager.domain.model.RoleEntity;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) throws NoClassFoundException{
        if (roleDTO == null || roleDTO.getRoleName() == null) {
            throw new CustomIllegalArgumentException("Role name cannot be null or empty.");
        }
        Optional<RoleEntity> existingRole = roleRepository.findByRoleName(roleDTO.getRoleName());
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Already exists a role with that name");
        }
        RoleEntity roleEntity = modelMapper.map(roleDTO, RoleEntity.class);
        roleRepository.save(roleEntity);
        return modelMapper.map(roleEntity, RoleDTO.class);
    }

    @Override
    public RoleDTO findRoleById(Long id) throws NoClassFoundException {

        if (id.equals(null) || id < 0) {
            throw new IllegalArgumentException("ID value is incorrect " + id);
        }
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new NoClassFoundException("Role no existe."));

        return modelMapper.map(roleEntity, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAllRoles() throws NoClassFoundException {

        List<RoleEntity> roleEntities = roleRepository.findAll();

        if (roleEntities.isEmpty() || roleEntities == null) {
            throw new NoClassFoundException("No se encontraron roles");
        }

        try {
            List<RoleDTO> roleDTOS = roleEntities.stream().map(roleEntity -> modelMapper.map(roleEntity, RoleDTO.class)).collect(Collectors.toList());

            if (roleDTOS == null || roleDTOS.isEmpty()) {
                throw new ClassNotFoundException("Error al mapear roles a DTO.");
            }
            return roleDTOS;
        } catch (Exception e) {
            throw new NoClassFoundException("Error al recibir los roles " + e.getMessage());
        }
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) throws NoClassFoundException {

        if (roleDTO == null || roleDTO.getRoleName() == null || roleDTO.getRoleName().toString().trim().isEmpty() || roleDTO.getId() == null) {
            throw new NoRolesFoundException("El campo roleName no puede estar vacio o nulo");
        }

        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new NoRolesFoundException("El rol con " + id + " no existe."));

        roleEntity.setRoleName(roleDTO.getRoleName());

        roleRepository.save(roleEntity);

        return modelMapper.map(roleEntity, RoleDTO.class);
    }

    @Override
    public void deleteRoleById(Long id) throws NoClassFoundException{

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id no puede ser nulo o menor o igual a 0");
        }
        if (!roleRepository.existsById(id)) {
            throw new NoRolesFoundException("El rol con " + id + " no existe.");
        }
        roleRepository.deleteById(id);

    }
}
