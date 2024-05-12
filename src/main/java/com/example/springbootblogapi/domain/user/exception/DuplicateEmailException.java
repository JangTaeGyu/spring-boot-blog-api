package com.example.springbootblogapi.domain.user.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class DuplicateEmailException extends HttpException {
    private final static String MESSAGE = "Duplicate Email Exist";

    public DuplicateEmailException() {
        super(MESSAGE);
    }
}
