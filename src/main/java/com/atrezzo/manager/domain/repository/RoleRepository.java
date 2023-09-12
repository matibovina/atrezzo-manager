package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(Roles roleName);

}
