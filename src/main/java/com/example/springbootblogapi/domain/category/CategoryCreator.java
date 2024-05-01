package com.example.springbootblogapi.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreator {
    private final CategoryRepository categoryRepository;

    public Long create(CategoryData data) {
        return categoryRepository.create(data.toEntity());
    }
}
