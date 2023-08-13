package com.atrezzo.manager.domain.model.enums;

public enum EventStatus {

    PENDING("Pendiente"),
    DONE("Realizado"),
    DELIVERED("Entregado"),
    FINISHED("Finalizado");

    private String displayName;

    EventStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
