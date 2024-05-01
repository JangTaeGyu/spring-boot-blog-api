package com.example.springbootblogapi.domain.category;

import java.util.Optional;

public interface CategoryRepository {
    Long create(Category category);

    boolean existsById(Long categoryId);

    Optional<Category> findById(Long categoryId);
}
