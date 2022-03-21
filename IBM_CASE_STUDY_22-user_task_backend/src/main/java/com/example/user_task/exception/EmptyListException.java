package com.example.user_task.exception;

public class EmptyListException extends RuntimeException {

    private String message;

    public EmptyListException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
