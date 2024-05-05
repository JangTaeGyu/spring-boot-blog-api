package com.example.springbootblogapi.domain.post;

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
        @Sql(value = "/sql/post-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("create - 포스트 생성")
    void create() {
        Post post = new Post(3L, "Post Title 11", "Post Body 11", true);
        Long createdPostId = postRepository.create(post);

        Optional<Post> result = postRepository.findById(createdPostId);
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Post foundPost = result.get();
            assertThat(post.getCategoryId()).isEqualTo(foundPost.getCategoryId());
            assertThat(post.getTitle()).isEqualTo(foundPost.getTitle());
            assertThat(post.getBody()).isEqualTo(foundPost.getBody());
            assertThat(post.isShow()).isEqualTo(foundPost.isShow());
        }
    }

    @Test
    @DisplayName("create - 포스트 생성 오류 - DataIntegrityViolationException")
    void create_DataIntegrityViolationException() {
        assertThatThrownBy(() -> {
            Post post = new Post(null, "Post Title 11", "Post Body 11", true);
            postRepository.create(post);
        }).isInstanceOf(DataIntegrityViolationException.class);

        assertThatThrownBy(() -> {
            Post post = new Post(3L, null, "Post Body 11", true);
            postRepository.create(post);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("findById = 포스트 조회")
    void findById() {
        Long postId = 8L;

        Optional<Post> result = postRepository.findById(postId);
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Post post = result.get();
            assertThat(post.getId()).isEqualTo(postId);
        }
    }

    @Test
    @DisplayName("findById = 포스트 조회 - isEmpty")
    void findById_isEmpty() {
        Optional<Post> result = postRepository.findById(25L);
        assertThat(result.isEmpty()).isTrue();
    }
}