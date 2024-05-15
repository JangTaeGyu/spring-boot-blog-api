package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.post.PostChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostChecker postChecker;
    private final CommentRepository commentRepository;

    public void getTopLevelCategories(Long postId) {
        postChecker.checkExistence(postId);
    }
}
