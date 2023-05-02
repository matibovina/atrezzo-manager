package com.atrezzo.manager.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Roles {

    ROLE_ADMIN("admin"),
    ROLE_CLIENT("client"),
    ROLE_WORKER("worker");

    private String label;

    private Roles(String label) {
        this.label = label;
    }

}
