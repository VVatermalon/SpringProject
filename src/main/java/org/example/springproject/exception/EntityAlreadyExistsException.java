package org.example.springproject.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    private final String message;
    public EntityAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}