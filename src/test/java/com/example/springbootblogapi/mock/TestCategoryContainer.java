package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.category.CategoryCreator;
import com.example.springbootblogapi.domain.category.CategoryQuery;
import com.example.springbootblogapi.domain.category.CategoryRepository;
import com.example.springbootblogapi.domain.category.CategoryUpdater;

public class TestCategoryContainer {
    public final CategoryRepository categoryRepository;
    public final CategoryQuery categoryQuery;
    public final CategoryCreator categoryCreator;
    public final CategoryUpdater categoryUpdater;

    public TestCategoryContainer() {
        this.categoryRepository = new FakeCategoryRepository();
        this.categoryQuery = new CategoryQuery(this.categoryRepository);
        this.categoryCreator = new CategoryCreator(this.categoryRepository);
        this.categoryUpdater = new CategoryUpdater(this.categoryRepository);
    }
}
