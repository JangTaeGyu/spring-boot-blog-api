package com.example.springbootblogapi.domain.post.data;

import lombok.Getter;

import java.util.List;

@Getter
public class PostTagData {
    private final List<String> tagNames;

    public PostTagData(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
