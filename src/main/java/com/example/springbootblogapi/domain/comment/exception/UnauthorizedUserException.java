package com.example.springbootblogapi.domain.comment.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class UnauthorizedUserException extends HttpException {
    private final static String MESSAGE = "User is not the writer of the comment";

    public UnauthorizedUserException() {
        super(MESSAGE);
    }
}
