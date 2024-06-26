package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentQuery {
    private final CommentRepository commentRepository;

    public Page<CommentDto> searchCommentsBy(CommentSearchData request, Pageable pageable) {
        return commentRepository.searchCommentsBy(request, pageable);
    }

    public List<PostCommentDto> getPostComments(Long postId) {
        return commentRepository.findAllPostCommentsByPostId(postId);
    }

    public List<PostCommentDto> getReplyPostComments(Long postId, Long commentId) {
        return commentRepository.findAllPostCommentsByPostIdAndParentId(postId, commentId);
    }
}
