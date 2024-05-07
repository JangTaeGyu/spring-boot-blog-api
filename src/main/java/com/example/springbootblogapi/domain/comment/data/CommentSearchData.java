package com.example.springbootblogapi.domain.comment.data;

import lombok.Getter;

@Getter
public class CommentSearchData {
    private final String keyword;
    private final Boolean show;

    public CommentSearchData(String keyword, Boolean show) {
        this.keyword = keyword;
        this.show = show;
    }
}
