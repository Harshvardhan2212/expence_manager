package com.example.expense_tracker.exception;

import java.time.LocalDateTime;

public record ExceptionDetails(
    LocalDateTime timestamp,
    String message,
    String details,
    String errorcode) {
}
