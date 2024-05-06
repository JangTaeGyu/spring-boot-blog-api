package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.domain.post.exception.PostNotFoundException;
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

    public PostDto getPostById(Long postId) {
        return postRepository.findPostById(postId)
                .orElseThrow(() -> new PostNotFoundException("postId", postId));
    }
}
