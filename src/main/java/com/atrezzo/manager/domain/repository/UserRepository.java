package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.enums.Roles;
import com.atrezzo.manager.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u JOIN u.roles ur JOIN ur.role r WHERE r.roleName IN :roleNames")
    List<UserEntity> findAllByRoleNames(@Param("roleNames") List<Roles> roleNames);

}
