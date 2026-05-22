package com.example.expense_tracker.exception;

public class ResourseNotFoundException extends RuntimeException {
  public ResourseNotFoundException(String message) {
    super(message);
  }
}
