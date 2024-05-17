package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentData;
import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import com.example.springbootblogapi.domain.post.PostChecker;
import com.example.springbootblogapi.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostChecker postChecker;
    private final CommentQuery commentQuery;
    private final CommentCreator commentCreator;

    public List<PostCommentDto> getPostComments(Long postId) {
        postChecker.checkExistence(postId);
        return commentQuery.getPostComments(postId);
    }

    public List<PostCommentDto> getReplyPostComments(Long postId, Long commentId) {
        postChecker.checkExistence(postId);
        return commentQuery.getReplyPostComments(postId, commentId);
    }

    public void createPostComment(User user, Long postId, CommentData data) {
        commentCreator.createComment(user.getId(), postId, data);
    }
}
