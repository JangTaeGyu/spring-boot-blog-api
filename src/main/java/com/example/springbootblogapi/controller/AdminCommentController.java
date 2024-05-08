package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.response.PaginationResponse;
import com.example.springbootblogapi.domain.comment.CommentService;
import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
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
@RequestMapping("/api/admin/comments")
@RequiredArgsConstructor
public class AdminCommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(defaultValue = "", required = false) Boolean show,
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PAGE, required = false) int page,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PER_PAGE, required = false) int perPage
    ) {
        CommentSearchData request = new CommentSearchData(keyword, show);
        Pageable pageable = PageRequest.of(page, perPage);
        Page<CommentDto> pageComment = commentService.searchCommentsBy(request, pageable);
        PaginationResponse<List<CommentDto>> response = new PaginationResponse<>(pageComment.getContent(), PaginatedData.of(pageComment));
        return ResponseEntity.ok(response);
    }
}
