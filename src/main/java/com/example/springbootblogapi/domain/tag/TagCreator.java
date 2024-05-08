package com.example.springbootblogapi.domain.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagCreator {
    private final TagRepository tagRepository;

    public Long createTag(String name) {
        return tagRepository.create(new Tag(name));
    }
}
