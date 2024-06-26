package com.example.springbootblogapi.domain.post.data;

import com.example.springbootblogapi.domain.post.Post;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class PostData {
    private final Long categoryId;
    private final String title;
    private final String body;

    public PostData(Long categoryId, String title, String body) {
        Assert.notNull(categoryId, "category id not null");
        Assert.notNull(title, "title not null");

        this.categoryId = categoryId;
        this.title = title;
        this.body = body;
    }

    public Post toEntity() {
        return new Post(this.categoryId, this.title, this.body, false);
    }
}
