package com.example.user_task.exception;

public class TaskNotFoundException extends RuntimeException{
    private String message;
    public TaskNotFoundException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
