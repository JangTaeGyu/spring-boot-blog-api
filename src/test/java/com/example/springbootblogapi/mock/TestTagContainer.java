package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.tag.TagCreator;
import com.example.springbootblogapi.domain.tag.TagRepository;

public class TestTagContainer {
    public final TagRepository tagRepository;
    public final TagCreator tagCreator;

    public TestTagContainer() {
        this.tagRepository = new FakeTagRepository();
        this.tagCreator = new TagCreator(this.tagRepository);
    }
}
