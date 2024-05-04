package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.support.exception.HttpException;

public class CategoryNotFoundException extends HttpException {
    private final static String MESSAGE = "category not found";

    public CategoryNotFoundException(String key, Object data) {
        super(MESSAGE);
    }
}
