package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.comment.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaCommentRepository extends Repository<Comment, Long> {
    Optional<Comment> findById(Long id);

    @Query(value = "select c from Comment c where c.id = :id and c.postId = :postId and c.show = true")
    Optional<Comment> findByIdAndPostId(Long id, Long postId);
    void save(Comment comment);
}
