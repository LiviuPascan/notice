package com.springliviu.notice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // @ControllerAdvice intercepts exceptions globally
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // handles validation errors
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>(); // response map
        Map<String, String> errors = new HashMap<>(); // validation errors

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage()); // collects field errors
        }

        response.put("timestamp", LocalDateTime.now()); // adds timestamp
        response.put("status", HttpStatus.BAD_REQUEST.value()); // 400 status code
        response.put("errors", errors); // adds error messages

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // returns 400 response
    }

    @ExceptionHandler(Exception.class) // handles any uncaught exception
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>(); // fallback response
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500 status code
        response.put("error", ex.getMessage()); // error message

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // returns 500 response
    }
}
