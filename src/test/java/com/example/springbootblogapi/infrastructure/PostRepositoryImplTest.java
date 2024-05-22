package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.Post;
import com.example.springbootblogapi.domain.post.PostRepository;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.infrastructure.core.TestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Import(PostRepositoryImpl.class)
@SqlGroup({
        @Sql(value = "/sql/category-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/post-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class PostRepositoryImplTest extends TestRepository {
    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("searchBy - 포스트 검색 - categoryId")
    void searchByCategoryId() {
        Pageable pageable = PageRequest.of(0, 10);
        PostSearchData searchData = new PostSearchData(2L, null, null);

        Page<PostDto> pagePost = postRepository.searchBy(searchData, pageable);
        pagePost.getContent().forEach(post -> {
            assertThat(post.getCategory().getId()).isEqualTo(searchData.getCategoryId());
        });
    }

    @Test
    @DisplayName("searchBy - 포스트 검색 - show")
    void searchByShow() {
        Pageable pageable = PageRequest.of(0, 10);
        PostSearchData searchData = new PostSearchData(null, true, null);

        Page<PostDto> pagePost = postRepository.searchBy(searchData, pageable);
        pagePost.getContent().forEach(post -> {
            assertThat(post.isShow()).isTrue();
        });
    }

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