package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.dto.PostTagDto;

import java.util.List;

public interface PostTagRepository {
    void create(PostTag postTag);
    List<PostTagDto> findAllTagsByPostIds(List<Long> postIds);
    void deleteByPostId(Long postId);
}
