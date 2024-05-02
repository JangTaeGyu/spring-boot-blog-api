package com.example.springbootblogapi.controller.admin.response;

import com.example.springbootblogapi.domain.category.CategoryDto;
import lombok.Getter;

@Getter
public class CategoryResponse {
    private final CategoryDto data;

    public CategoryResponse(CategoryDto data) {
        this.data = data;
    }
}
