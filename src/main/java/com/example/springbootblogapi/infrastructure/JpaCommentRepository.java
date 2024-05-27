package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.comment.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaCommentRepository extends Repository<Comment, Long> {
    @Query(value = "select c from Comment c where c.id = :id and c.deletedAt is null")
    Optional<Comment> findById(Long id);

    @Query(value = "select c from Comment c where c.id = :id and c.postId = :postId and c.show = true and c.deletedAt is null")
    Optional<Comment> findByIdAndPostId(Long id, Long postId);
    Comment save(Comment comment);
}
