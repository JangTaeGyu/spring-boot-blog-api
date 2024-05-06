package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.data.CategoryData;
import com.example.springbootblogapi.domain.category.data.CategorySortData;
import com.example.springbootblogapi.domain.category.exception.CategoryNotFoundException;
import com.example.springbootblogapi.mock.TestCategoryContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CategoryUpdaterTest {
    private final CategoryUpdater categoryUpdater;

    public CategoryUpdaterTest() {
        TestCategoryContainer testContainer = new TestCategoryContainer();
        this.categoryUpdater = testContainer.categoryUpdater;
    }

    @Test
    @DisplayName("updateCategoryById - 카테고리 수정")
    void updateCategoryById() {
        assertDoesNotThrow(() -> {
            CategoryData categoryData = new CategoryData("Category", "Category Description");
            categoryUpdater.updateCategoryById(3L, categoryData);
        });
    }

    @Test
    @DisplayName("updateCategoryById - 카테고리 수정 오류 - CategoryNotFoundException")
    void updateCategoryById_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            CategoryData categoryData = new CategoryData("Category", "Category Description");
            categoryUpdater.updateCategoryById(6L, categoryData);
        }).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("setCategoryVisibility - 카테고리 활성화, 비활성황화 처리")
    void setCategoryVisibility() {
        assertDoesNotThrow(() -> {
            categoryUpdater.setCategoryVisibility(5L, true);
        });
    }

    @Test
    @DisplayName("setCategoryVisibility - 카테고리 활성화, 비활성황화 처리 오류 - CategoryNotFoundException")
    void setCategoryVisibility_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            categoryUpdater.setCategoryVisibility(6L, true);
        }).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("sortCategories - 카테고리 정렬")
    void sortCategories() {
        assertDoesNotThrow(() -> {
            CategorySortData data = new CategorySortData(List.of(1L, 2L));
            categoryUpdater.sortCategories(data);
        });
    }

    @Test
    @DisplayName("sortCategories - 카테고리 정렬 오류 - CategoryNotFoundException")
    void sortCategories_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            CategorySortData data = new CategorySortData(List.of(1L, 2L, 6L, 7L));
            categoryUpdater.sortCategories(data);
        }).isInstanceOf(CategoryNotFoundException.class);
    }
}