package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentQuery {
    private final CommentRepository commentRepository;

    public Page<CommentDto> searchCommentsBy(CommentSearchData request, Pageable pageable) {
        return commentRepository.searchCommentsBy(request, pageable);
    }
}
