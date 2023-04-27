package com.atrezzo.manager.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Roles {

    ADMIN_ROLE("admin"),
    CLIENT_ROLE("client"),
    WORKER_ROLE("worker");

    private String label;

    private Roles(String label) {
        this.label = label;
    }

}
