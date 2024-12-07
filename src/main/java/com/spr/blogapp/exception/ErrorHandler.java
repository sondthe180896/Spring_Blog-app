package com.spr.blogapp.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler
        implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code , Object... args){
        //Object... args dùng để truyền đối số vào {0} và {1}
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request)
    {
        var message = getMessage("MethodArgumentNotValidException.message");
        var errors = new HashMap<String, String>();
        var timestamp = LocalDateTime.now().toString();
        for (var error : exception.getFieldErrors()){
            var key = error.getField();
            var value = error.getDefaultMessage();
            errors.put(key, value);
        }
        var response = new ErrorResponse(timestamp, message, errors);
        return new ResponseEntity<>(response, headers, status);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public  ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception){
        var message = getMessage("ConstraintViolationException.message");
        var errors = new HashMap<String, String>();
        var timestamp = LocalDateTime.now().toString();
        for(var constraint : exception.getConstraintViolations()){
            var key = constraint.getPropertyPath().toString();
            var value = constraint.getMessage();
            errors.put(key, value);
        }
        var response = new ErrorResponse(timestamp,message, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {
        var message = getMessage("HttpRequestMethodNotSupportedException.message", exception.getMethod());
        var response = new ErrorResponse(message, LocalDateTime.now().toString());
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status,
                                                                     WebRequest request) {
        var message = getMessage("HttpMediaTypeNotSupportedException.message",ex.getContentType());
        var response = new ErrorResponse(message, LocalDateTime.now().toString());
        return  new ResponseEntity<>(response, headers, status);
    }
}
