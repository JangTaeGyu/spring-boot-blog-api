package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.exception.PostDisabledException;
import com.example.springbootblogapi.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostChecker {
    private final PostRepository postRepository;

    public void checkExistence(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("postId", postId));

        if (!post.isShow()) {
            throw new PostDisabledException();
        }
    }
}
