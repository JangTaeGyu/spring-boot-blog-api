package com.example.springbootblogapi.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class CategoryUpdater {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void update(Long categoryId, CategoryData data) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("categoryId", categoryId))
                .update(data);
    }

    @Transactional
    public void updateShow(Long categoryId, boolean show) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("categoryId", categoryId))
                .changeShow(show);
    }

    public void sort(CategorySortData data) {
        Long count = categoryRepository.getCountByIds(data.getIds());
        if (count != data.getIds().size()) {
            throw new CategoryNotFoundException("categoryIds", data.getIds());
        }

        AtomicInteger sort = new AtomicInteger(1);

        data.getIds().forEach(categoryId -> {
            categoryRepository.updateSortById(sort.getAndIncrement(), categoryId);
        });
    }
}
