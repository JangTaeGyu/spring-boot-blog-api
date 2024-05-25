package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.CategoryRepository;
import com.example.springbootblogapi.domain.tag.TagRepository;
import com.example.springbootblogapi.infrastructure.core.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;

@SqlGroup({
        @Sql(value = "/sql/tag-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TagRepositoryImplTest extends TestRepository {
    @Autowired
    private TagRepositoryImpl tagRepository;

    @Test
    void create() {
    }

    @Test
    void findByName() {
    }
}