package com.example.springbootblogapi.controller.admin.response;

import lombok.Getter;

@Getter
public class CreatedResponse {
    private final Long id;

    public CreatedResponse(Long id) {
        this.id = id;
    }
}
