package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.Post;
import org.springframework.data.repository.Repository;

public interface JpaPostRepository extends Repository<Post, Long> {
    Post save(Post post);
}
