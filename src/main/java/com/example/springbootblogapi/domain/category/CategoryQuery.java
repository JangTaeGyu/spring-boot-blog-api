package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.dto.CategoryDto;
import com.example.springbootblogapi.domain.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryQuery {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllSortedCategories() {
        return categoryRepository.findAllSortedAscOrder();
    }

    public CategoryDto getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(Category::toDto)
                .orElseThrow(() -> new CategoryNotFoundException("categoryId", categoryId));
    }

    public void checkCategory(Long categoryId) {
        boolean exists = categoryRepository.existsById(categoryId);
        if (!exists) {
            throw new CategoryNotFoundException("categoryId", categoryId);
        }
    }
}
