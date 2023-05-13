package com.atrezzo.manager.presentation.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtrezzoResponse {

    private int status;
    private String message;
    private Object data;

}
