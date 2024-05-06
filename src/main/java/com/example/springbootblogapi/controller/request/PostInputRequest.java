package com.example.springbootblogapi.controller.request;

import com.example.springbootblogapi.domain.post.data.PostData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class PostInputRequest {
    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    public PostData toPostData() {
        return new PostData(categoryId, title, body);
    }
}
