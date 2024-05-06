package com.example.springbootblogapi.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
    Page<PostDto> searchPostsBy(PostSearchData postSearchData, Pageable pageable);

    Long create(Post post);

    Optional<Post> findById(Long postId);
}
