package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostData;
import com.example.springbootblogapi.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostRepository postRepository;
    private final PostValidation postValidation;

    @Transactional
    public void updatePostById(Long postId, PostData data) {
        postValidation.validate(data);
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("postId", postId))
                .update(data);
    }

    @Transactional
    public void setPostVisibility(Long postId, boolean show) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("postId", postId))
                .changeShow(show);
    }
}
