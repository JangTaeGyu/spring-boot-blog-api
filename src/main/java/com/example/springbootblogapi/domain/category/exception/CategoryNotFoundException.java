package com.example.springbootblogapi.domain.category.exception;

import com.example.springbootblogapi.support.exception.HttpException;

public class CategoryNotFoundException extends HttpException {
    private final static String MESSAGE = "Category Not Found";

    public CategoryNotFoundException(String key, Object data) {
        super(MESSAGE);
    }
}
