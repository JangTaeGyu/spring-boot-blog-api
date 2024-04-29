package com.example.springbootblogapi.domain.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/category-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("create - 카테고리 생성")
    void create() {
        Category category = new Category("Category 06", "Category Description 06", true);
        Long createdCategoryId = categoryRepository.create(category);

        Optional<Category> result = categoryRepository.findById(createdCategoryId);
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Category foundCategory = result.get();
            assertThat(category.getName()).isEqualTo(foundCategory.getName());
            assertThat(category.getDescription()).isEqualTo(foundCategory.getDescription());
            assertThat(category.isShow()).isEqualTo(foundCategory.isShow());
        }
    }

    @Test
    @DisplayName("create - 카테고리 생성 오류 - DataIntegrityViolationException")
    void create_DataIntegrityViolationException() {
        Category category = new Category(null, "Category Description 06", true);

        assertThatThrownBy(() -> {
            categoryRepository.create(category);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void existsById() {
    }

    @Test
    void findById() {
    }
}