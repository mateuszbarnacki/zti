package com.example.zti.exceptions;

public class PathNotFoundException extends Exception {
    public PathNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public PathNotFoundException(final String message) {
        super(message);
    }
}
