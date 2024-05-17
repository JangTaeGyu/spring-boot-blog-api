package com.example.springbootblogapi.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCommentDto {
    private final Long id;
    private final String body;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    private final Long countOfComments;

    private final CommentUser writer;

    @Getter
    private static class CommentUser {
        private final Long id;
        private final String email;
        private final String name;

        public CommentUser(Long id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }
    }

    @QueryProjection
    public PostCommentDto(
            Long id,
            String body,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long countOfComments,
            Long userId,
            String userEmail,
            String userName
    ) {
        this.id = id;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.countOfComments = countOfComments;
        this.writer = new CommentUser(userId, userEmail, userName);
    }
}
