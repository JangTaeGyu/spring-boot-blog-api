package com.example.springbootblogapi.domain.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagCreator {
    private final TagRepository tagRepository;

    public Long createTag(String name) {
        return tagRepository.create(new Tag(name));
    }

    public Long getOrCreateTagId(String name) {
        Optional<Tag> result = tagRepository.findByName(name);
        return result.isEmpty() ? createTag(name) : result.get().getId();
    }
}
