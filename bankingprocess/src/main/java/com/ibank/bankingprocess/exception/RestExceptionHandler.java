package com.ibank.bankingprocess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RestExceptionHandler {

    // Handle NoAccountsFoundException and return a 404 response
    @ExceptionHandler(NoAccountsFoundException.class)
    public ResponseEntity<String> handleNoAccountsFoundException(NoAccountsFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // You can add more exception handlers here for other types of exceptions
}
