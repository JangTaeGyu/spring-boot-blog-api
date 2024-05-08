package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentQuery commentQuery;
    private final CommentUpdater commentUpdater;

    public Page<CommentDto> searchCommentsBy(CommentSearchData request, Pageable pageable) {
        return commentQuery.searchCommentsBy(request, pageable);
    }


    public void setCommentVisibility(Long commentId, boolean show) {
        commentUpdater.setCommentVisibility(commentId, show);
    }
}
