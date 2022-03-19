package com.example.user_task.exception;
import com.example.user_task.exception.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskControllerExceptionHandler {
    private final ErrorResponseModel errorResponseModel;

    @Autowired
    public TaskControllerExceptionHandler(ErrorResponseModel errorResponseModel) {
        this.errorResponseModel = errorResponseModel;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handleTaskNotFoundException(TaskNotFoundException e)
    {
        errorResponseModel.setErrorMessage(e.getMessage());
        errorResponseModel.setErrorTime(System.currentTimeMillis());
        errorResponseModel.setErrorCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseModel);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handleTaskListEmptyException(EmptyListException e)
    {
        errorResponseModel.setErrorMessage(e.getMessage());
        errorResponseModel.setErrorTime(System.currentTimeMillis());
        errorResponseModel.setErrorCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseModel);
    }
}