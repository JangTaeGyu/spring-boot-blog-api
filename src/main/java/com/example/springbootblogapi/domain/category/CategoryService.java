package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.data.CategoryData;
import com.example.springbootblogapi.domain.category.data.CategorySortData;
import com.example.springbootblogapi.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryQuery categoryQuery;
    private final CategoryCreator categoryCreator;
    private final CategoryUpdater categoryUpdater;

    public List<CategoryDto> getAllSortedCategories() {
        return categoryQuery.getAllSortedCategories();
    }

    public Long createCategory(CategoryData data) {
        return categoryCreator.createCategory(data);
    }

    public void updateCategoryById(Long categoryId, CategoryData data) {
        categoryUpdater.updateCategoryById(categoryId, data);
    }

    public void setCategoryVisibility(Long categoryId, boolean show) {
        categoryUpdater.setCategoryVisibility(categoryId, show);
    }

    public void sortCategories(CategorySortData data) {
        categoryUpdater.sortCategories(data);
    }
}
