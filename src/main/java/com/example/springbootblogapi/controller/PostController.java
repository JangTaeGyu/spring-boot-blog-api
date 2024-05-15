package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.response.PaginationResponse;
import com.example.springbootblogapi.controller.response.SuccessfulResponse;
import com.example.springbootblogapi.domain.post.UserPostService;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.support.constant.PageConstant;
import com.example.springbootblogapi.support.data.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final UserPostService userPostService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(defaultValue = "", required = false) Long categoryId,
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PAGE, required = false) int page,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PER_PAGE, required = false) int perPage
    ) {
        PostSearchData request = new PostSearchData(categoryId, true, keyword);
        Page<PostDto> pagePost = userPostService.searchPostsBy(request, PageRequest.of(page, perPage));
        PaginationResponse<List<PostDto>> response = new PaginationResponse<>(pagePost.getContent(), PaginatedData.of(pagePost));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> show(@PathVariable Long postId) {
        PostDto post = userPostService.getPostById(postId);
        SuccessfulResponse<PostDto> response = new SuccessfulResponse<>(post);
        return ResponseEntity.ok(response);
    }
}
