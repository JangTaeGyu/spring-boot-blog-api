package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.tag.Tag;
import com.example.springbootblogapi.domain.tag.TagRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final JpaTagRepository tagRepository;
    private final JPAQueryFactory query;

    @Override
    public Long create(Tag tag) {
        return tagRepository.save(tag).getId();
    }

    @Override
    public Optional<Tag> findByName(String tagName) {
        return tagRepository.findByName(tagName);
    }
}
