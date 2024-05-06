package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.response.PaginationResponse;
import com.example.springbootblogapi.domain.post.PostService;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.support.constant.PageConstant;
import com.example.springbootblogapi.support.data.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
