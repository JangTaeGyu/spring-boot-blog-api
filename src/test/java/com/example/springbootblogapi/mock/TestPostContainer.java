package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.post.PostCreator;
import com.example.springbootblogapi.domain.post.PostQuery;
import com.example.springbootblogapi.domain.post.PostRepository;

public class TestPostContainer {
    public final PostRepository postRepository;
    public final PostQuery postQuery;
    public final PostCreator postCreator;

    public TestPostContainer() {
        TestCategoryContainer categoryContainer = new TestCategoryContainer();

        this.postRepository = new FakePostRepository();
        this.postQuery = new PostQuery(this.postRepository);
        this.postCreator = new PostCreator(this.postRepository);
    }
}
