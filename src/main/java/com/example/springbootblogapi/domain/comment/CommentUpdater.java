package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CommentUpdater {
    private final CommentRepository commentRepository;

    @Transactional
    public void setCommentVisibility(Long commentId, boolean show) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("commentId", commentId))
                .changeShow(show);
    }
}
