package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepository {
    Page<CommentDto> searchCommentsBy(CommentSearchData searchData, Pageable pageable);

    Optional<Comment> findById(Long commentId);
}
