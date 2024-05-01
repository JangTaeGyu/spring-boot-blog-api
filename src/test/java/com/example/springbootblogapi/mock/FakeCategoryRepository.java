package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.category.Category;
import com.example.springbootblogapi.domain.category.CategoryData;
import com.example.springbootblogapi.domain.category.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class FakeCategoryRepository implements CategoryRepository {
    private final AtomicLong id = new AtomicLong(1L);
    private final List<Category> data = new ArrayList<>();

    public FakeCategoryRepository() {
        IntStream.range(1, 6).forEach(i -> {
            Category category = Category.fakeCategory(id.getAndIncrement());
            category.update(new CategoryData("Category 0" + i, "Category Description 0" + i));
            this.data.add(category);
        });
    }

    @Override
    public Long create(Category category) {
        Category createdCategory = Category.fakeCategory(id.getAndIncrement());
        return createdCategory.getId();
    }

    @Override
    public boolean existsById(Long categoryId) {
        Optional<Category> result = data.stream().filter(category -> category.getId().equals(categoryId)).findFirst();
        return result.isPresent();
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return data.stream().filter(category -> category.getId().equals(categoryId)).findFirst();
    }
}
