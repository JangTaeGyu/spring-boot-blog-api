package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.PostTag;
import com.example.springbootblogapi.domain.post.PostTagKey;
import org.springframework.data.repository.Repository;

public interface JpaPostTagRepository extends Repository<PostTag, PostTagKey> {
    void save(PostTag postTag);
}
