package com.example.zti.exceptions;

public class DishNotFoundException extends Exception {
    public DishNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public DishNotFoundException(final String message) {
        super(message);
    }
}
