package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.comment.Comment;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaCommentRepository extends Repository<Comment, Long> {
    Optional<Comment> findById(Long id);
}
