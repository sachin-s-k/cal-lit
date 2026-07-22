package com.example.cal_lit_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError>handlNotFoundException(NotFoundException ex){
        ApiError error= new ApiError(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(    MethodArgumentNotValidException exception){
        Map<String,String> error= new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(errors->{
            error.put(errors.getField(),errors.getDefaultMessage());

        });




        ValidationErrorResponse response = new ValidationErrorResponse(400,
                "Validation failed",
                LocalDateTime.now(),
                error);
        return ResponseEntity.badRequest()
                .body(response);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentials(
            BadCredentialsException ex) {

        ApiError error = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid email or password",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }

}
