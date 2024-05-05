package com.example.springbootblogapi.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;

    public Long createPost(PostData data) {
        return postCreator.createPost(data);
    }
}
