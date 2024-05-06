package com.example.springbootblogapi.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostQuery {
    private final PostRepository postRepository;

    public Page<PostDto> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        return postRepository.searchBy(searchData, pageable);
    }
}
