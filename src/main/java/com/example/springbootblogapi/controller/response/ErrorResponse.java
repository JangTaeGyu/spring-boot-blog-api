package com.example.springbootblogapi.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
    private final Date datetime = new Date();
    private final Integer status;
    private final String path;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String details;

    public ErrorResponse(Integer status, String path, String message) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.details = null;
    }

    public ErrorResponse(Integer status, String path, String message, String details) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.details = details;
    }
}
