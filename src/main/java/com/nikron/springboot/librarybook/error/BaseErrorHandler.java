package com.nikron.springboot.librarybook.error;

public class BaseErrorHandler extends Exception {
    public BaseErrorHandler(String message) {
        super(message);
    }
}
