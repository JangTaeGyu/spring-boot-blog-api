package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.Category;
import com.example.springbootblogapi.domain.comment.Comment;
import com.example.springbootblogapi.domain.comment.CommentRepository;
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

@Import(CommentRepositoryImpl.class)
@SqlGroup({
        @Sql(value = "/sql/category-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/post-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/comment-repository-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CommentRepositoryImplTest extends TestRepository {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("findById - 댓글 조회")
    void findById() {
        Optional<Comment> result = commentRepository.findById(1L);
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Comment foundComment = result.get();
            assertThat(foundComment.getId()).isEqualTo(1L);
        }
    }

    @Test
    @DisplayName("findById - 댓글 조회 - isEmpty")
    void findById_isEmpty() {
        Optional<Comment> result = commentRepository.findById(99L);
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("findById - 포스트 아이디로 댓글 조회")
    void findByIdAndPostId() {
        Optional<Comment> result = commentRepository.findByIdAndPostId(1L, 1L);
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Comment foundComment = result.get();
            assertThat(foundComment.getId()).isEqualTo(1L);
            assertThat(foundComment.getPostId()).isEqualTo(1L);
        }
    }

    @Test
    @DisplayName("create - 댓글 생성")
    void create() {
        Comment comment = new Comment("Post 1, Comment 06", 1L, null, null);
        Long createdCommentId = commentRepository.create(comment);

        Optional<Comment> result = commentRepository.findById(createdCommentId);
        assertThat(result.isPresent()).isTrue();

        if (result.isPresent()) {
            Comment foundComment = result.get();
            assertThat(comment.getBody()).isEqualTo(foundComment.getBody());
            assertThat(comment.isShow()).isTrue();
            assertThat(comment.getPostId()).isEqualTo(foundComment.getPostId());
            assertThat(comment.getParentId()).isNull();
            assertThat(comment.getUserId()).isNull();
        }
    }

    @Test
    @DisplayName("create - 댓글 생성 오류 - DataIntegrityViolationException")
    void create_DataIntegrityViolationException() {
        assertThatThrownBy(() -> {
            Comment comment = new Comment("Post 1, Comment 06", null, null, null);
            commentRepository.create(comment);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}