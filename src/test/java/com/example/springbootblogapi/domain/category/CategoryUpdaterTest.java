package com.example.springbootblogapi.domain.category;

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
    @DisplayName("update - 카테고리 수정")
    void update() {
        assertDoesNotThrow(() -> {
            CategoryData categoryData = new CategoryData("Category", "Category Description");
            categoryUpdater.update(3L, categoryData);
        });
    }

    @Test
    @DisplayName("update - 카테고리 수정 오류 - CategoryNotFoundException")
    void updateCategory_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            CategoryData categoryData = new CategoryData("Category", "Category Description");
            categoryUpdater.update(6L, categoryData);
        }).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("updateShow - 카테고리 활성화, 비활성황화 처리")
    void updateShow() {
        assertDoesNotThrow(() -> {
            categoryUpdater.updateShow(5L, true);
        });
    }

    @Test
    @DisplayName("updateShow - 카테고리 활성화, 비활성황화 처리 오류 - CategoryNotFoundException")
    void updateShow_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            categoryUpdater.updateShow(6L, true);
        }).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("sort - 카테고리 정렬")
    void sort() {
        assertDoesNotThrow(() -> {
            CategorySortData data = new CategorySortData(List.of(1L, 2L));
            categoryUpdater.sort(data);
        });
    }

    @Test
    @DisplayName("sort - 카테고리 정렬 오류 - CategoryNotFoundException")
    void sortCategories_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            CategorySortData data = new CategorySortData(List.of(1L, 2L, 6L, 7L));
            categoryUpdater.sort(data);
        }).isInstanceOf(CategoryNotFoundException.class);
    }
}