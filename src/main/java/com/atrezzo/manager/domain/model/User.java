package com.atrezzo.manager.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

    @JsonIgnore
    private List<UserRole> roles;

    private Boolean enabled;

    private LocalDateTime creationDate;

}
