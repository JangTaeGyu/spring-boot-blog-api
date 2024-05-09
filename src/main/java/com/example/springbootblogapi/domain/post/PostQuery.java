package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.domain.post.dto.PostTagDto;
import com.example.springbootblogapi.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQuery {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    private void mergeTagsToPost(List<PostDto> posts) {
        if (posts.isEmpty()) return;

        List<Long> postIds = posts.stream().map(PostDto::getId).toList();
        List<PostTagDto> tags = postTagRepository.findAllTagsByPostIds(postIds);

        posts.forEach(post -> {
            List<PostTagDto> filteredTags = tags.stream().filter(tag -> tag.getPostId().equals(post.getId())).toList();
            post.setTags(filteredTags);
        });
    }

    public Page<PostDto> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        Page<PostDto> pagePost = postRepository.searchBy(searchData, pageable);
        mergeTagsToPost(pagePost.getContent());
        return pagePost;
    }

    public PostDto getPostById(Long postId) {
        return postRepository.findPostById(postId)
                .orElseThrow(() -> new PostNotFoundException("postId", postId));
    }
}
