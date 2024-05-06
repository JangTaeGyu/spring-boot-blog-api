package com.example.springbootblogapi.domain.category.data;

import lombok.Getter;

import java.util.List;

@Getter
public class CategorySortData {
    private final List<Long> ids;

    public CategorySortData(List<Long> ids) {
        this.ids = ids;
    }
}
