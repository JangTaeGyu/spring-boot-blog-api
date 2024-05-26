package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.tag.Tag;
import com.example.springbootblogapi.domain.tag.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class FakeTagRepository implements TagRepository {
    private final AtomicLong id = new AtomicLong(1L);
    private final List<Tag> data = new ArrayList<>();

    public FakeTagRepository() {
        List<String> tagNames = List.of("Java 17", "Spring Boot 2.7", "Spring Security", "Spring Data JPA", "QueryDSL");
        IntStream.range(1, 6).forEach(i -> {
            Tag tag = Tag.fakeEntity(id.getAndIncrement(),tagNames.get(i - 1));
            this.data.add(tag);
        });
    }

    @Override
    public Long create(Tag tag) {
        Tag createdTag = Tag.fakeEntity(id.getAndIncrement(), tag.getName());
        return createdTag.getId();
    }

    @Override
    public Optional<Tag> findByName(String tagName) {
        return data.stream().filter(tag -> tag.getName().equals(tagName)).findFirst();
    }
}
