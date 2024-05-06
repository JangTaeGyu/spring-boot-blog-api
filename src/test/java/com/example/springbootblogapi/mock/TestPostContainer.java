package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.post.PostCreator;
import com.example.springbootblogapi.domain.post.PostRepository;

public class TestPostContainer {
    public final PostRepository postRepository;
    public final PostCreator postCreator;

    public TestPostContainer() {
        TestCategoryContainer categoryContainer = new TestCategoryContainer();

        this.postRepository = new FakePostRepository();
        this.postCreator = new PostCreator(this.postRepository);
    }
}
