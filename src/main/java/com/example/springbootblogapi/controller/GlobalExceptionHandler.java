package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.response.ErrorResponse;
import com.example.springbootblogapi.controller.response.ValidationResponse;
import com.example.springbootblogapi.support.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationResponse response = new ValidationResponse( httpStatus.value(), request.getRequestURI(), "Validation Error", errors );
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException e, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(e.getHttpStatus().value(), request.getRequestURI(), e.getMessage());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = new ErrorResponse(httpStatus.value(), request.getRequestURI(), "Server Error", e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}
