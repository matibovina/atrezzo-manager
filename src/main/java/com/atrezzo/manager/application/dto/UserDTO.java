package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String email;

    private String password;

    private List<Role> roles;

    private Boolean enabled;

    private LocalDateTime creationDate;

}
