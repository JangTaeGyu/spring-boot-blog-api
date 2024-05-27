package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Page<CommentDto> searchCommentsBy(CommentSearchData searchData, Pageable pageable);
    List<PostCommentDto> findAllPostCommentsByPostId(Long postId);
    List<PostCommentDto> findAllPostCommentsByPostIdAndParentId(Long postId, Long parentId);
    Optional<Comment> findById(Long commentId);
    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);
    Long create(Comment comment);
}
