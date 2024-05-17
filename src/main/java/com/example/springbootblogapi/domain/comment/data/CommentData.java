package com.example.springbootblogapi.domain.comment.data;

import com.example.springbootblogapi.domain.comment.Comment;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class CommentData {
    private final String body;
    private final Long parentId;

    public CommentData(String body, Long parentId) {
        Assert.notNull(body, "body not null");

        this.body = body;
        this.parentId = parentId;
    }

    public Comment toEntity(Long postId, Long userId) {
        return new Comment(body, postId, parentId, userId);
    }
}
