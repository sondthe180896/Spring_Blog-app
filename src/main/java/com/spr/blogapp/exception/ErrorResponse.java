package com.spr.blogapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private String timestamp;
    private String message;
    private Map<String, String> errors;

    public ErrorResponse(String message, String timestamp) {
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorResponse(String timestamp, String message, Map<String, String> errors) {
        this.timestamp = LocalDateTime.now().toString();
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(String message, String timestamp, HttpStatus httpStatus) {
        this.message = message;
        this.timestamp = timestamp;

    }
}
