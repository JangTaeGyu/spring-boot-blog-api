package com.example.springbootblogapi.domain.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryDto> findAllSortedAscOrder();

    Long create(Category category);

    boolean existsById(Long categoryId);

    Optional<Category> findById(Long categoryId);

    Long getCountByIds(List<Long> categoryIds);

    void updateSortById(Integer sort, Long categoryId);
}
