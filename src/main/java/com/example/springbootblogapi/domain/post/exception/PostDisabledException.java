package com.example.springbootblogapi.domain.post.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class PostDisabledException extends HttpException {
    private final static String MESSAGE = "Disabled Post";

    public PostDisabledException() {
        super(MESSAGE);
    }
}
