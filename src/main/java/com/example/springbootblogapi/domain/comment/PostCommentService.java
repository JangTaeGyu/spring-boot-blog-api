package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.comment.data.CommentData;
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
    private final CommentCreator commentCreator;
    private final CommentUpdater commentUpdater;
    private final CommentDeleter commentDeleter;

    public List<PostCommentDto> getPostComments(Long postId) {
        postChecker.checkExistence(postId);
        return commentQuery.getPostComments(postId);
    }

    public List<PostCommentDto> getReplyPostComments(Long postId, Long commentId) {
        postChecker.checkExistence(postId);
        return commentQuery.getReplyPostComments(postId, commentId);
    }

    public void createPostComment(Long postId, CommentData data) {
        postChecker.checkExistence(postId);
        commentCreator.createComment(postId, data);
    }

    public void updatePostComment(Long postId, Long commentId, CommentData data) {
        postChecker.checkExistence(postId);
        commentUpdater.updateComment(postId, commentId, data);
    }

    public void deletePostComment(Long postId, Long commentId, Long userId) {
        postChecker.checkExistence(postId);
        commentDeleter.deleteComment(postId, commentId, userId);
    }
}
