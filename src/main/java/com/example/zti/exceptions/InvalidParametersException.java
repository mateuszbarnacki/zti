package com.example.zti.exceptions;

public class InvalidParametersException extends Exception {
    public InvalidParametersException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public InvalidParametersException(final String message) {
        super(message);
    }
}
