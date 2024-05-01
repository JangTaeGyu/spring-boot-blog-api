package com.example.springbootblogapi.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryQuery {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllSorted() {
        return categoryRepository.findAllSortedAscOrder();
    }
}
