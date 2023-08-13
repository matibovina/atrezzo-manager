package com.atrezzo.manager.presentation.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(NoClassFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomNoClassFoundException(NoClassFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomIllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleCustomIllegalArgumentException(CustomIllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
