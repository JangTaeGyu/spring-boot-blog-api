package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CommentDeleter {
    private final CommentRepository commentRepository;

    @Transactional
    public void deleteComment(Long postId, Long commentId) {
        commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new CommentNotFoundException("commentId", commentId))
                .delete();
    }
}
