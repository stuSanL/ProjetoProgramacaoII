package com.exception;

public class NullInputException extends RuntimeException {
    public NullInputException(String campo) {
        super(campo + " não pode ser nulo.");
    }
}
