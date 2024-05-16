package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.response.SuccessfulResponse;
import com.example.springbootblogapi.domain.comment.PostCommentService;
import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class PostCommentController {
    private final PostCommentService postCommentService;

    @GetMapping
    public ResponseEntity<?> index(@PathVariable Long postId) {
        List<PostCommentDto> comments = postCommentService.getPostComments(postId);
        SuccessfulResponse<List<PostCommentDto>> response = new SuccessfulResponse<>(comments);
        return ResponseEntity.ok(response);
    }
}
