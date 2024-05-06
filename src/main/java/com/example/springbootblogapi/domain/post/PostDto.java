package com.example.springbootblogapi.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private final Long id;
    private final String title;
    private final String body;
    private final boolean show;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    private final PostCategory category;

    @Getter
    public static class PostCategory {
        private final Long id;
        private final String name;
        private final boolean show;

        public PostCategory(Long id, String name, boolean show) {
            this.id = id;
            this.name = name;
            this.show = show;
        }
    }

    @QueryProjection
    public PostDto(
            Long id,
            String title,
            String body,
            boolean show,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long categoryId,
            String categoryName,
            boolean categoryShow
    ) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.show = show;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = new PostCategory(categoryId, categoryName, categoryShow);
    }
}
