package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.tag.Tag;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaTagRepository extends Repository<Tag, Long> {
    Tag save(Tag tag);

    Optional<Tag> findByName(String name);
}
