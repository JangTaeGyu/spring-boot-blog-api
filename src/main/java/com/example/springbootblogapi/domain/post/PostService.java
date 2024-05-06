package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostData;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostQuery postQuery;
    private final PostCreator postCreator;

    public Page<PostDto> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        return postQuery.searchPostsBy(searchData, pageable);
    }

    public Long createPost(PostData data) {
        return postCreator.createPost(data);
    }
}
