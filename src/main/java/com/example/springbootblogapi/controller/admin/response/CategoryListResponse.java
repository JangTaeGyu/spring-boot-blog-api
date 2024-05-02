package com.example.springbootblogapi.controller.admin.response;

import com.example.springbootblogapi.domain.category.CategoryDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryListResponse {
    private final List<CategoryDto> data;

    public CategoryListResponse(List<CategoryDto> data) {
        this.data = data;
    }
}
