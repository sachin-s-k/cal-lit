package com.example.cal_lit_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse extends ApiError{
    private Map<String, String > errors;

    public ValidationErrorResponse(int status, String message, LocalDateTime timeStamp,Map<String, String> errors) {
        super(status, message, timeStamp);
        this.errors=errors;
    }
}
