package com.ibank.bankingprocess.exception;

public class NoAccountsFoundException extends RuntimeException {

    public NoAccountsFoundException(String message) {
        super(message);
    }
}
