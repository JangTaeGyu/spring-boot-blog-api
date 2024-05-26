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
    @DisplayName("getOrCreateTagId - 태그 없으면 생성 또는 조회")
    void getOrCreateTagId() {
        Long createdTagId01 = tagCreator.getOrCreateTagId("PHP");
        assertThat(createdTagId01).isEqualTo(6L);

        Long createdTagId02 = tagCreator.getOrCreateTagId("Java 17");
        assertThat(createdTagId02).isEqualTo(1L);
    }
}