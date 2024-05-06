package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.exception.CategoryNotFoundException;
import com.example.springbootblogapi.mock.TestCategoryContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CategoryDeleterTest {
    private final CategoryDeleter categoryDeleter;

    public CategoryDeleterTest() {
        TestCategoryContainer testContainer = new TestCategoryContainer();
        this.categoryDeleter = testContainer.categoryDeleter;
    }

    @Test
    @DisplayName("deleteCategoryById - 카테고리 삭제")
    void deleteCategoryById() {
        assertDoesNotThrow(() -> {
            categoryDeleter.deleteCategoryById(5L);
        });
    }

    @Test
    @DisplayName("deleteCategoryById - 카테고리 삭제 오류 CategoryNotFoundException")
    void deleteCategoryById_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            categoryDeleter.deleteCategoryById(6L);
        }).isInstanceOf(CategoryNotFoundException.class);
    }
}