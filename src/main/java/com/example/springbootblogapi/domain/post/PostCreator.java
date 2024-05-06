package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreator {
    private final PostRepository postRepository;
    private final PostValidation postValidation;

    public Long createPost(PostData data) {
        postValidation.validate(data);
        return postRepository.create(data.toEntity());
    }
}
