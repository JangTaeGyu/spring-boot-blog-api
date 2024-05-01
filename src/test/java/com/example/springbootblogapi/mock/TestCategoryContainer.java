package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.category.CategoryCreator;
import com.example.springbootblogapi.domain.category.CategoryQuery;
import com.example.springbootblogapi.domain.category.CategoryRepository;

public class TestCategoryContainer {
    public final CategoryRepository categoryRepository;
    public final CategoryQuery categoryQuery;
    public final CategoryCreator categoryCreator;

    public TestCategoryContainer() {
        this.categoryRepository = new FakeCategoryRepository();
        this.categoryQuery = new CategoryQuery(this.categoryRepository);
        this.categoryCreator = new CategoryCreator(this.categoryRepository);
    }
}
