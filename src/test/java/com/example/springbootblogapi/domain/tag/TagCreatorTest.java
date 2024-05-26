package com.example.springbootblogapi.domain.tag;

import com.example.springbootblogapi.mock.TestTagContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TagCreatorTest {
    private final TagCreator tagCreator;

    public TagCreatorTest() {
        TestTagContainer testContainer = new TestTagContainer();
        this.tagCreator = testContainer.tagCreator;
    }

    @Test
    @DisplayName("createTag - 태그 생성")
    void createTag() {
        Long createdTagId = tagCreator.createTag("PHP");

        assertThat(createdTagId).isPositive();
    }

    @Test
    void getOrCreateTagId() {
    }
}