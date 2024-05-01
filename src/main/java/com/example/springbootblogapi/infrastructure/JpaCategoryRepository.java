package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaCategoryRepository extends Repository<Category, Long> {
    @Query(value = "SELECT MAX(sort) FROM Category")
    Integer getMaxSort();

    boolean existsById(Long id);

    Category save(Category category);

    Optional<Category> findById(Long id);

    void delete(Category category);
}
