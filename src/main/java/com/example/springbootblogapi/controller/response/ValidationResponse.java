package com.example.springbootblogapi.controller.response;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationResponse extends ErrorResponse {
    private final Map<String, String> errors;

    public ValidationResponse(Integer status, String path, String code, Map<String, String> errors) {
        super(status, path, code);
        this.errors = errors;
    }
}
