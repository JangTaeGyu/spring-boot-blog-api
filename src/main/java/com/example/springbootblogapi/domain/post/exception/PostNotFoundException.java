package com.example.springbootblogapi.domain.post.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class PostNotFoundException extends HttpException {
    private final static String MESSAGE = "Post Not Found";

    public PostNotFoundException(String key, Object data) {
        super(MESSAGE);
    }
}
