package com.atrezzo.manager.infrastructure.exceptions;

public class NoClientsFoundException extends RuntimeException{
    public NoClientsFoundException(String message) {
        super(message);
    }
}
