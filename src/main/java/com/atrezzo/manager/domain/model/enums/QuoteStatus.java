package com.atrezzo.manager.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum QuoteStatus {

    REQUESTED,
    SENT,
    ACCEPTED,
    REJECTED

}
