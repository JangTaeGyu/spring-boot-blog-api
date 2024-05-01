package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.mock.TestCategoryContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CategoryQueryTest {
    private final CategoryQuery categoryQuery;

    public CategoryQueryTest() {
        TestCategoryContainer testContainer = new TestCategoryContainer();
        this.categoryQuery = testContainer.categoryQuery;
    }

    @Test
    @DisplayName("getAllSorted - 정렬된 카테고리 목록 조회")
    void getAllSorted() {
        List<CategoryDto> categories = categoryQuery.getAllSorted();

        assertThat(categories.size()).isEqualTo(5L);
    }

    @Test
    @DisplayName("getBy - 카테고리 조회 오류 - CategoryNotFoundException")
    void getBy_CategoryNotFoundException() {
        assertThatThrownBy(() -> {
            categoryQuery.getBy(6L);
        }).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("getBy - 카테고리 조회")
    void getBy() {
        Long categoryId = 3L;
        CategoryDto category = categoryQuery.getBy(categoryId);

        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(categoryId);
        assertThat(category.getName()).isEqualTo("Category 03");
        assertThat(category.getDescription()).isEqualTo("Category Description 03");
    }
}