package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.data.CategoryData;
import com.example.springbootblogapi.domain.category.data.CategorySortData;
import com.example.springbootblogapi.domain.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CategoryUpdater {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void updateCategoryById(Long categoryId, CategoryData data) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("categoryId", categoryId))
                .update(data);
    }

    @Transactional
    public void setCategoryVisibility(Long categoryId, boolean show) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("categoryId", categoryId))
                .changeShow(show);
    }

    public void sortCategories(CategorySortData data) {
        Long count = categoryRepository.getCountByIds(data.getIds());
        if (count != data.getIds().size()) {
            throw new CategoryNotFoundException("categoryIds", data.getIds());
        }

        int sort = 1;
        for (Long categoryId : data.getIds()) {
            categoryRepository.updateSortById(sort++, categoryId);
        };
    }
}
