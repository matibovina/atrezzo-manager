package com.atrezzo.manager.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO implements Serializable {


    private Long id;

    @JsonIgnore
    private UserDTO user;

    @JsonIgnore
    private RoleDTO role;

    @Serial
    private static final long serialVersionUID = -631269837387310888L;
}
