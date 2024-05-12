package com.example.springbootblogapi.domain.user.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class NotMatchPasswordException extends HttpException {
    private final static String MESSAGE = "Not Match Password";

    public NotMatchPasswordException() {
        super(MESSAGE);
    }
}
