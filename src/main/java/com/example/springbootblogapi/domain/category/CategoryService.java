package com.example.springbootblogapi.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryQuery categoryQuery;
    private final CategoryCreator categoryCreator;

    public List<CategoryDto> getAllSorted() {
        return categoryQuery.getAllSorted();
    }

    public Long create(CategoryData data) {
        return categoryCreator.create(data);
    }
}
