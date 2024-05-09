package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.tag.TagCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostTagManager {
    private final TagCreator tagCreator;
    private final PostTagRepository postTagRepository;

    public void attachTagsToPost(Long postId, List<String> tagNames) {
        tagNames.forEach(name -> {
            PostTagKey postTagKey = new PostTagKey(postId, tagCreator.getOrCreateTagId(name));
            PostTag postTag = new PostTag(postTagKey);
            postTagRepository.create(postTag);
        });
    }

    public void detachTagsToPost(Long postId) {
        postTagRepository.deleteByPostId(postId);
    }

    public void syncTagsToPost(Long postId, List<String> tagNames) {
        detachTagsToPost(postId);
        attachTagsToPost(postId, tagNames);
    }
}
