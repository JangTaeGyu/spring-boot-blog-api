package com.example.springbootblogapi.controller.admin.request;

import com.example.springbootblogapi.domain.category.CategoryData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CategoryInputRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public CategoryData toData() {
        return new CategoryData(name, description);
    }
}
