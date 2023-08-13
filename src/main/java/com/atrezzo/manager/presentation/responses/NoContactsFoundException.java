package com.atrezzo.manager.presentation.responses;

public class NoContactsFoundException extends RuntimeException{

    public NoContactsFoundException(String message) {
        super(message);
    }

}
