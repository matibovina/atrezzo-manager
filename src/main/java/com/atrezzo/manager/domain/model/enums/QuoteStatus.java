package com.atrezzo.manager.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum QuoteStatus {

    REQUESTED("requested"),
    SENT("sent"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private String quoteStatus;

    QuoteStatus(String quoteStatus) {this.quoteStatus = quoteStatus;}


}
