package com.example.springbootblogapi.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PostTagDto {
    private final Long id;
    private final String name;

    @JsonIgnore
    private final Long postId;

    @QueryProjection
    public PostTagDto(Long id, String name, Long postId) {
        this.id = id;
        this.name = name;
        this.postId = postId;
    }
}
