package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.mock.TestCategoryContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryCreatorTest {
    private final CategoryCreator categoryCreator;

    public CategoryCreatorTest() {
        TestCategoryContainer testContainer = new TestCategoryContainer();
        this.categoryCreator = testContainer.categoryCreator;
    }

    @Test
    @DisplayName("create - 카테고리 생성")
    void create() {
        CategoryData data = new CategoryData("Category 06", "Category Description 06");
        Long createdCategoryId = categoryCreator.create(data);

        assertThat(createdCategoryId).isPositive();
    }
}