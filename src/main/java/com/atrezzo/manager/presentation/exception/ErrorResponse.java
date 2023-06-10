package com.atrezzo.manager.presentation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String errorClass;
    private String message;
    private int status;
}
