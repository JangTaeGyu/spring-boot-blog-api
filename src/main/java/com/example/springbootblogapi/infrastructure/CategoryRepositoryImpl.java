package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.Category;
import com.example.springbootblogapi.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository categoryRepository;

    @Transactional
    @Override
    public Long create(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Override
    public boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
