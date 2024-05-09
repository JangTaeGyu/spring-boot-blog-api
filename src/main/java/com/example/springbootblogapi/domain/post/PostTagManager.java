package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostTagData;
import com.example.springbootblogapi.domain.tag.TagCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTagManager {
    private final TagCreator tagCreator;
    private final PostTagRepository postTagRepository;

    public void attachTagsToPost(Long postId, PostTagData tagData) {
        tagData.getTagNames().forEach(tagName -> {
            PostTagKey postTagKey = new PostTagKey(postId, tagCreator.getOrCreateTagId(tagName));
            postTagRepository.create(new PostTag(postTagKey));
        });
    }

    public void detachTagsToPost(Long postId) {
        postTagRepository.deleteByPostId(postId);
    }

    public void syncTagsToPost(Long postId, PostTagData tagData) {
        detachTagsToPost(postId);
        attachTagsToPost(postId, tagData);
    }
}
