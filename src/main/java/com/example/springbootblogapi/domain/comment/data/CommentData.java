package com.example.springbootblogapi.domain.comment.data;

import com.example.springbootblogapi.domain.comment.Comment;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class CommentData {
    private final String body;
    private final Long parentId;
    private final Long userId;


    public CommentData(String body, Long parentId, Long userId) {
        Assert.notNull(body, "body not null");
        Assert.notNull(userId, "userId not null");

        this.body = body;
        this.parentId = parentId;
        this.userId = userId;
    }

    public Comment toEntity(Long postId) {
        return new Comment(body, postId, parentId, userId);
    }
}
