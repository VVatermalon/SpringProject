package org.example.springproject.exception;

public class NoSuchEntityException extends RuntimeException {

    private final String message;
    public NoSuchEntityException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
