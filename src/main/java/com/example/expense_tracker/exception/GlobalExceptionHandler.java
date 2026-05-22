package com.example.expense_tracker.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourseNotFoundException.class)
  public ResponseEntity<ExceptionDetails> handleCategoryNotFound(Exception exception, WebRequest request) {
    ExceptionDetails exceptionDetails = new ExceptionDetails(
        LocalDateTime.now(),
        exception.getMessage(),
        request.getDescription(false),
        "CATEGORY_NOT_FOUND");
    return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
  }

}
