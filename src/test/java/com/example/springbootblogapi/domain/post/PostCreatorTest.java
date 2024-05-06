package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.post.data.PostData;
import com.example.springbootblogapi.mock.TestPostContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PostCreatorTest {
    private final PostCreator postCreator;

    public PostCreatorTest() {
        TestPostContainer testContainer = new TestPostContainer();
        this.postCreator = testContainer.postCreator;
    }

    @Test
    @DisplayName("createPost - 포스트 생성")
    void createPost() {
        PostData postData = new PostData(3L, "Post Title 11", "Post Body 11");
        Long createdPostId = postCreator.createPost(postData);

        assertThat(createdPostId).isPositive();
    }
}