package com.juzhen.common.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }
 
    public UserNotFoundException(String message) {
        super(message);
    }
 
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

