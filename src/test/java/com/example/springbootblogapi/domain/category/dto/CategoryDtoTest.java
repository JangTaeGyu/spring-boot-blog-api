package com.example.springbootblogapi.domain.category.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryDtoTest {
    @Test
    @DisplayName("createCategoryDto - 카테고리 Dto 생성")
    void createCategoryDto() {
        Long categoryId = 5L;
        String categoryName = "Category Name";
        String categoryDescription = "Category Description";
        boolean categoryShow = true;
        Integer categorySort = 5;
        LocalDateTime categoryCreatedAt = LocalDateTime.now();
        LocalDateTime categoryUpdatedAt = LocalDateTime.now();
        Long countOfPosts = 0L;

        CategoryDto category = new CategoryDto(
                categoryId,
                categoryName,
                categoryDescription,
                categoryShow,
                categorySort,
                categoryCreatedAt,
                categoryUpdatedAt,
                countOfPosts
        );

        assertThat(category.getId()).isEqualTo(categoryId);
        assertThat(category.getName()).isEqualTo(categoryName);
        assertThat(category.getDescription()).isEqualTo(categoryDescription);
        assertThat(category.isShow()).isEqualTo(categoryShow);
        assertThat(category.getSort()).isEqualTo(categorySort);
        assertThat(category.getCreatedAt()).isEqualTo(categoryCreatedAt);
        assertThat(category.getUpdatedAt()).isEqualTo(categoryUpdatedAt);
        assertThat(category.getCountOfPosts()).isEqualTo(countOfPosts);
    }
}