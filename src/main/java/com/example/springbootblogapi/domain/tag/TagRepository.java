package com.example.springbootblogapi.domain.tag;

import java.util.Optional;

public interface TagRepository {
    Long create(Tag tag);

    Optional<Tag> findByName(String tagName);
}
