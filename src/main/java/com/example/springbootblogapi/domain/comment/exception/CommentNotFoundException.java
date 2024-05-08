package com.example.springbootblogapi.domain.comment.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class CommentNotFoundException extends HttpException {
    private final static String MESSAGE = "Comment Not Found";

    public CommentNotFoundException(String key, Object data) {
        super(MESSAGE);
    }
}
