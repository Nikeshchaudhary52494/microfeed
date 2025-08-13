package com.microfeed.authservice.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microfeed.authservice.dto.UserResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMsg = Optional.ofNullable(ex.getBindingResult().getFieldError())
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("Validation error");

        return ResponseEntity.badRequest().body(UserResponse.builder()
                .success(false)
                .message(errorMsg)
                .build());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<UserResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(UserResponse.builder()
                .success(false)
                .message("email aready exixt")
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UserResponse.builder()
                .success(false)
                .message("Internal server error: " + ex.getMessage())
                .build());
    }
}
