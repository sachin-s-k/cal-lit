package com.example.cal_lit_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Map<String,String> errors= new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());

        });




        ValidationErrorResponse response = new ValidationErrorResponse(400,
                "Validation failed",
                LocalDateTime.now(),
                errors);
        return ResponseEntity.badRequest()
                .body(response);

    }

}
