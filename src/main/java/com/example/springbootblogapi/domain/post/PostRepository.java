package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
    Page<PostDto> searchBy(PostSearchData postSearchData, Pageable pageable);

    Long create(Post post);

    Optional<Post> findById(Long postId);
}
