package com.example.springbootblogapi.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryDto {
    private final Long id;
    private final String name;
    private final String description;
    private final boolean show;
    private final Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long countOfPosts;

    public CategoryDto(
            Long id,
            String name,
            String description,
            boolean show,
            Integer sort,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.show = show;
        this.sort = sort;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @QueryProjection
    public CategoryDto(
            Long id,
            String name,
            String description,
            boolean show,
            Integer sort,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long countOfPosts
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.show = show;
        this.sort = sort;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.countOfPosts = countOfPosts;
    }
}
