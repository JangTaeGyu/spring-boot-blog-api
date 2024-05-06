package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.request.PostInputRequest;
import com.example.springbootblogapi.controller.response.CreatedResponse;
import com.example.springbootblogapi.controller.response.PaginationResponse;
import com.example.springbootblogapi.controller.response.SuccessfulResponse;
import com.example.springbootblogapi.domain.post.PostService;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.support.constant.PageConstant;
import com.example.springbootblogapi.support.data.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/posts")
@RequiredArgsConstructor
public class AdminPostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(defaultValue = "", required = false) Long categoryId,
            @RequestParam(defaultValue = "", required = false) Boolean show,
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PAGE, required = false) int page,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PER_PAGE, required = false) int perPage
    ) {
        PostSearchData request = new PostSearchData(categoryId, show, keyword);
        Pageable pageable = PageRequest.of(page, perPage);
        Page<PostDto> pagePost = postService.searchPostsBy(request, pageable);
        PaginationResponse<List<PostDto>> response = new PaginationResponse<>(pagePost.getContent(), PaginatedData.of(pagePost));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid PostInputRequest request) {
        Long postId = postService.createPost(request.toPostData());
        CreatedResponse response = new CreatedResponse(postId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> show(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        SuccessfulResponse<PostDto> response = new SuccessfulResponse<>(post);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable Long postId, @RequestBody @Valid PostInputRequest request) {
        postService.updatePostById(postId, request.toPostData());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{postId}/enable")
    public ResponseEntity<Void> enable(@PathVariable Long postId) {
        postService.setPostVisibility(postId, true);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{postId}/disable")
    public ResponseEntity<Void> disable(@PathVariable Long postId) {
        postService.setPostVisibility(postId, false);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }
}
