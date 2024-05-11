package com.example.springbootblogapi.domain.user.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class AuthenticationFailureException extends HttpException {
    private final static String MESSAGE = "Authentication Failed";

    public AuthenticationFailureException() {
        super(MESSAGE);
    }
}
