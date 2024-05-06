package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreator {
    private final PostRepository postRepository;

    public Long createPost(PostData data) {
        return postRepository.create(data.toEntity());
    }
}
