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

    @QueryProjection
    public PostCommentDto(Long id, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
