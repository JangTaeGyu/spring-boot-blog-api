package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import com.example.springbootblogapi.domain.post.PostChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostChecker postChecker;
    private final CommentQuery commentQuery;

    public List<PostCommentDto> getPostComments(Long postId) {
        postChecker.checkExistence(postId);
        return commentQuery.getPostComments(postId);
    }

    public List<PostCommentDto> getReplyPostComments(Long postId, Long commentId) {
        return commentQuery.getReplyPostComments(postId, commentId);
    }
}
