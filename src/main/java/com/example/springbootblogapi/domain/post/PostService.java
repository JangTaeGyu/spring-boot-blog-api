package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostData;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.data.PostTagData;
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
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final PostTagManager postTagManager;

    public Page<PostDto> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        return postQuery.searchPostsBy(searchData, pageable);
    }

    public Long createPost(PostData data, PostTagData tagData) {
        Long createdPostId =  postCreator.createPost(data);
        postTagManager.attachTagsToPost(createdPostId, tagData);
        return createdPostId;
    }

    public PostDto getPostById(Long postId) {
        return postQuery.getPostById(postId);
    }

    public void updatePostById(Long postId, PostData postData, PostTagData tagData) {
        postUpdater.updatePostById(postId, postData);
        postTagManager.syncTagsToPost(postId, tagData);
    }

    public void setPostVisibility(Long postId, boolean show) {
        postUpdater.setPostVisibility(postId, show);
    }

    public void deletePostById(Long postId) {
        postDeleter.deletePostById(postId);
        postTagManager.detachTagsToPost(postId);
    }
}
