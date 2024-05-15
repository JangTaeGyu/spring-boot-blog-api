package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.domain.post.exception.PostDisabledException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPostService {
    private final PostQuery postQuery;

    public Page<PostDto> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        return postQuery.searchPostsBy(searchData, pageable);
    }

    public PostDto getPostById(Long postId) {
        PostDto post = postQuery.getPostById(postId);
        if (!post.isShow()) throw new PostDisabledException();
        return post;
    }
}
