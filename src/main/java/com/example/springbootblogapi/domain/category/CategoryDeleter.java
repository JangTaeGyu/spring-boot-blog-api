package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CategoryDeleter {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void deleteCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("categoryId", categoryId));
        categoryRepository.delete(category);
    }
}
