package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.category.data.CategoryData;
import com.example.springbootblogapi.domain.category.data.CategorySortData;
import com.example.springbootblogapi.domain.category.dto.CategoryDto;
import com.example.springbootblogapi.mock.TestCategoryContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CategoryServiceTest {
    private final CategoryService categoryService;

    public CategoryServiceTest() {
        TestCategoryContainer testContainer = new TestCategoryContainer();
        this.categoryService = new CategoryService(testContainer.categoryQuery, testContainer.categoryCreator, testContainer.categoryUpdater);
    }

    @Test
    @DisplayName("getAllSortedCategories - 정렬된 카테고리 목록 조회")
    void getAllSortedCategories() {
        List<CategoryDto> categories = categoryService.getAllSortedCategories();

        assertThat(categories.size()).isEqualTo(5L);
    }

    @Test
    @DisplayName("createCategory - 카테고리 생성")
    void createCategory() {
        CategoryData data = new CategoryData("Category 06", "Category Description 06");
        Long createdCategoryId = categoryService.createCategory(data);

        assertThat(createdCategoryId).isPositive();
    }

    @Test
    @DisplayName("updateCategoryById - 카테고리 수정")
    void updateCategoryById() {
        assertDoesNotThrow(() -> {
            CategoryData categoryData = new CategoryData("Category", "Category Description");
            categoryService.updateCategoryById(3L, categoryData);
        });
    }

    @Test
    @DisplayName("setCategoryVisibility - 카테고리 활성화, 비활성황화 처리")
    void setCategoryVisibility() {
        assertDoesNotThrow(() -> {
            categoryService.setCategoryVisibility(5L, true);
            categoryService.setCategoryVisibility(5L, false);
        });
    }

    @Test
    @DisplayName("sortCategories - 카테고리 정렬")
    void sortCategories() {
        assertDoesNotThrow(() -> {
            CategorySortData data = new CategorySortData(List.of(1L, 2L));
            categoryService.sortCategories(data);
        });
    }
}