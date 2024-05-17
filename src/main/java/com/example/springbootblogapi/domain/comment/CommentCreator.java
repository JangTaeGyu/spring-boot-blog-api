package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCreator {
    private final CommentRepository commentRepository;

    public void createComment(Long postId, CommentData data) {
        Comment comment = data.toEntity(postId);
        commentRepository.create(comment);
    }
}
