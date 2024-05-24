package com.example.springbootblogapi.domain.category.data;

import com.example.springbootblogapi.domain.category.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CategoryDataTest {
    @DisplayName("createCategoryData - 카테고리 데이터 생성 오류 - IllegalArgumentException")
    @Test
    void createCategoryData_IllegalArgumentException() {
        assertThatThrownBy(() -> new CategoryData("", null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new CategoryData(null, "")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new CategoryData(null, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("createCategoryData - 카테고리 데이터 생성")
    @Test
    void createCategoryData() {
        String categoryName = "category name";
        String categoryDescription = "category description";

        CategoryData data = new CategoryData(categoryName, categoryDescription);

        assertThat(data.getName()).isEqualTo(categoryName);
        assertThat(data.getDescription()).isEqualTo(categoryDescription);
    }

    @DisplayName("toEntity - CategoryData 에서 Category Entity 생성")
    @Test
    void toEntity() {
        String categoryName = "category name";
        String categoryDescription = "category description";

        CategoryData data = new CategoryData(categoryName, categoryDescription);
        Category category = data.toEntity();

        assertThat(category).isInstanceOf(Category.class);
        assertThat(category.getName()).isEqualTo(categoryName);
        assertThat(category.getDescription()).isEqualTo(categoryDescription);
        assertThat(category.isShow()).isTrue();
    }
}