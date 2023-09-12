package com.atrezzo.manager.presentation.exception;

public class CustomIllegalArgumentException extends RuntimeException{
    public CustomIllegalArgumentException(String message){
        super(message);
    }
}
