package com.atrezzo.manager.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordRequest {

    private String username;

    private String oldPassword;

    private String newPassword;
}
