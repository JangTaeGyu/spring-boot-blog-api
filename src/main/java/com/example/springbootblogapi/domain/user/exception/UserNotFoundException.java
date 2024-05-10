package com.example.springbootblogapi.domain.user.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class UserNotFoundException extends HttpException {
    private final static String MESSAGE = "User Not Found";

    public UserNotFoundException(String key, Object data) {
        super(MESSAGE);
    }
}
