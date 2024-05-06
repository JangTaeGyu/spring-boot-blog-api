package com.example.springbootblogapi.domain.post.data;

import lombok.Getter;

@Getter
public class PostSearchData {
    private final Long categoryId;
    private final Boolean show;
    private final String keyword;

    public PostSearchData(Long categoryId, Boolean show, String keyword) {
        this.categoryId = categoryId;
        this.show = show;
        this.keyword = keyword;
    }
}
