package com.atrezzo.manager.application.exceptions;

public class NoRolesFoundException extends RuntimeException{

    public NoRolesFoundException() {
        super();
    }

    public NoRolesFoundException(String message) {
        super(message);
    }
}
