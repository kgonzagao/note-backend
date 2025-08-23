package com.kgonzaga.note.app.exception;

import com.kgonzaga.note.app.util.DateTimeUtils;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final DateTimeUtils dateTimeUtils;

    public GlobalExceptionHandler(DateTimeUtils dateTimeUtils) {
        this.dateTimeUtils = dateTimeUtils;
    }

    // 1. Custom handler for not found exception
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<?> handleNoteNotFound(NoteNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
    }

    // 2. Handles validation errors from @Valid in controllers (DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", dateTimeUtils.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("errors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // 3. Handles validation errors from @Validated (e.g., in service layer)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> violations = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            violations.put(propertyPath, message);
        });

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", dateTimeUtils.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("errors", violations);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // 4. Generic fallback handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }

    // 5. Custom handler for optimistic locking conflicts.
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<?> handleOptimisticLocking(ObjectOptimisticLockingFailureException ex) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                "Conflict",
                "The resource was modified by another user. Please reload and try again."
        );
    }

    // 6.- Custom handler for unique values
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                "Conflict",
                "A resource with the same unique value already exists."
        );
    }

    // 7. Handles missing request body (e.g., when @RequestBody is expected but not provided)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                "Request body is required and was not provided."
        );
    }

    // Utility method for building consistent error responses
    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = Map.of(
                "timestamp", dateTimeUtils.now(),
                "status", status.value(),
                "error", error,
                "message", message
        );
        return ResponseEntity.status(status).body(body);
    }
}