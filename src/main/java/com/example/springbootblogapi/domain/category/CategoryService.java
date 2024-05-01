package com.example.springbootblogapi.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryCreator categoryCreator;

    public Long create(CategoryData data) {
        return categoryCreator.create(data);
    }
}
