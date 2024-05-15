package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.annotation.LoggedInUser;
import com.example.springbootblogapi.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class PostCommentController {

    @GetMapping
    public ResponseEntity<?> index(@LoggedInUser User user, @PathVariable Long postId) {
        System.out.println(user.getEmail());
        System.out.println(postId);
        return ResponseEntity.ok(null);
    }
}
