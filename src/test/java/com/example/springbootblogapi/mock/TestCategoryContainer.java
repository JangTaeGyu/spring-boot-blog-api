package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.category.*;

public class TestCategoryContainer {
    public final CategoryRepository categoryRepository;
    public final CategoryQuery categoryQuery;
    public final CategoryCreator categoryCreator;
    public final CategoryUpdater categoryUpdater;
    public final CategoryDeleter categoryDeleter;

    public TestCategoryContainer() {
        this.categoryRepository = new FakeCategoryRepository();
        this.categoryQuery = new CategoryQuery(this.categoryRepository);
        this.categoryCreator = new CategoryCreator(this.categoryRepository);
        this.categoryUpdater = new CategoryUpdater(this.categoryRepository);
        this.categoryDeleter = new CategoryDeleter(this.categoryRepository);
    }
}
