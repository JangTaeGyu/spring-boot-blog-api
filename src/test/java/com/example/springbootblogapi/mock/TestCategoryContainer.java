package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.category.CategoryCreator;
import com.example.springbootblogapi.domain.category.CategoryRepository;

public class TestCategoryContainer {
    public final CategoryRepository categoryRepository;
    public final CategoryCreator categoryCreator;

    public TestCategoryContainer() {
        this.categoryRepository = new FakeCategoryRepository();
        this.categoryCreator = new CategoryCreator(this.categoryRepository);
    }
}
