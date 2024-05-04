package com.example.springbootblogapi.controller.response;

import lombok.Getter;

@Getter
public class SuccessfulResponse<T> {
    private final T data;

    public SuccessfulResponse(T data) {
        this.data = data;
    }
}
