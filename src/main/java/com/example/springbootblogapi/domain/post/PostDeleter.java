package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostDeleter {
    private final PostRepository postRepository;

    @Transactional
    public void deletePostById(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("postId", postId))
                .delete();
    }
}
