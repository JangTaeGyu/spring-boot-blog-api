package com.example.springbootblogapi.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private final Long id;
    private final String body;
    private final boolean show;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    private final CommentPost post;

    private ParentComment parentComment;

    @Getter
    private static class CommentPost {
        private final Long id;
        private final String title;
        private final boolean show;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime deletedAt;

        public CommentPost(Long id, String title, boolean show, LocalDateTime deletedAt) {
            this.id = id;
            this.title = title;
            this.show = show;
            this.deletedAt = deletedAt;
        }
    }

    @Getter
    private static class ParentComment {
        private final Long id;
        private final String body;
        private final boolean show;

        public ParentComment(Long id, String body, boolean show) {
            this.id = id;
            this.body = body;
            this.show = show;
        }
    }

    @QueryProjection
    public CommentDto(
            Long id,
            String body,
            boolean show,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long postId,
            String postTitle,
            boolean postShow,
            LocalDateTime postDeletedAt,
            Long parentCommentId,
            String parentCommentBody,
            boolean parentCommentShow
    ) {
        this.id = id;
        this.body = body;
        this.show = show;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = new CommentPost(postId, postTitle, postShow, postDeletedAt);
        if (parentCommentId != null) {
            this.parentComment = new ParentComment(parentCommentId, parentCommentBody, parentCommentShow);
        }
    }
}
