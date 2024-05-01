package com.example.springbootblogapi.domain.category;

public class CategoryNotFoundException extends RuntimeException {
    private final static String MESSAGE = "category not found";

    public CategoryNotFoundException(String key, Object data) {
        super(MESSAGE);
    }
}
