package com.example.springbootblogapi.controller.request;

import com.example.springbootblogapi.domain.comment.data.CommentData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentInputRequest {
    @NotBlank
    private String body;
    private Long parentId;

    public CommentData toData(Long userId) {
        return new CommentData(body, parentId, userId);
    }
}
