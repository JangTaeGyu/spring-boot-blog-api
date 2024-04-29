package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.Category;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaCategoryRepository extends Repository<Category, Long> {
    boolean existsById(Long id);
    Category save(Category category);
    Optional<Category> findById(Long id);
}
