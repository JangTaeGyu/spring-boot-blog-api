package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.tag.Tag;
import com.example.springbootblogapi.domain.tag.TagRepository;
import com.example.springbootblogapi.infrastructure.core.TestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Import(TagRepositoryImpl.class)
@SqlGroup({
        @Sql(value = "/sql/tag-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TagRepositoryImplTest extends TestRepository {
    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("create - 태그 생성")
    void create() {
        Tag tag = new Tag("PHP");
        Long createdTagId = tagRepository.create(tag);
        assertThat(createdTagId).isPositive();

        Optional<Tag> result = tagRepository.findByName("PHP");
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Tag foundTag = result.get();
            assertThat(createdTagId).isEqualTo(foundTag.getId());
            assertThat(tag.getName()).isEqualTo(foundTag.getName());
        }
    }

    @Test
    @DisplayName("create - 태그 생성 오류 - DataIntegrityViolationException")
    void create_DataIntegrityViolationException() {
        assertThatThrownBy(() -> {
            tagRepository.create(new Tag("Java 17"));
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void findByName() {
    }
}