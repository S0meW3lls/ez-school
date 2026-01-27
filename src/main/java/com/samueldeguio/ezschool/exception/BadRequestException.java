package com.samueldeguio.ezschool.exception;

public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException() {
        super("Invalid request parameters");
    }
}
