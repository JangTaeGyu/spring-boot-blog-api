package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.mock.TestPostContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostQueryTest {
    private final PostQuery postQuery;

    public PostQueryTest() {
        TestPostContainer testContainer = new TestPostContainer();
        this.postQuery = testContainer.postQuery;
    }

    @Test
    void searchPostsBy() {
    }
}