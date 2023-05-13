package com.atrezzo.manager.application.util;

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
