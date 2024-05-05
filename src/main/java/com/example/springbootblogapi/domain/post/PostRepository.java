package com.example.springbootblogapi.domain.post;

import java.util.Optional;

public interface PostRepository {
    Long create(Post post);

    Optional<Post> findById(Long postId);
}
