package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.Category;
import com.example.springbootblogapi.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Long create(Category category) {
        return 0L;
    }

    @Override
    public boolean existsById(Long categoryId) {
        return false;
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return Optional.empty();
    }
}
