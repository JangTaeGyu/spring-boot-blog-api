package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.annotation.LoggedInUser;
import com.example.springbootblogapi.controller.request.CommentInputRequest;
import com.example.springbootblogapi.controller.response.SuccessfulResponse;
import com.example.springbootblogapi.domain.comment.PostCommentService;
import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import com.example.springbootblogapi.domain.user.data.LoggedInUserData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{commentId}/replies")
    public ResponseEntity<?> replies(@PathVariable Long postId, @PathVariable Long commentId) {
        List<PostCommentDto> comments = postCommentService.getReplyPostComments(postId, commentId);
        SuccessfulResponse<List<PostCommentDto>> response = new SuccessfulResponse<>(comments);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @LoggedInUser LoggedInUserData loggedInUserData,
            @PathVariable Long postId,
            @RequestBody @Valid CommentInputRequest request
    ) {
        postCommentService.createPostComment(postId, request.toData(loggedInUserData.getId()));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> update(
            @LoggedInUser LoggedInUserData loggedInUserData,
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentInputRequest request
    ) {
        postCommentService.updatePostComment(postId, commentId, request.toData(loggedInUserData.getId()));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @LoggedInUser LoggedInUserData loggedInUserData,
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        postCommentService.deletePostComment(postId, commentId, loggedInUserData.getId());
        return ResponseEntity.noContent().build();
    }
}
