package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.PostTag;
import com.example.springbootblogapi.domain.post.PostTagRepository;
import com.example.springbootblogapi.domain.post.dto.PostTagDto;
import com.example.springbootblogapi.domain.post.dto.QPostTagDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.springbootblogapi.domain.post.QPostTag.postTag;
import static com.example.springbootblogapi.domain.tag.QTag.tag;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostTagRepositoryImpl implements PostTagRepository {
    private final JpaPostTagRepository postTagRepository;
    private final JPAQueryFactory query;

    @Transactional
    @Override
    public void create(PostTag postTag) {
        postTagRepository.save(postTag);
    }

    @Override
    public List<PostTagDto> findAllTagsByPostIds(List<Long> postIds) {
        return query.select(new QPostTagDto(
                        tag.id,
                        tag.name,
                        postTag.id.postId
                ))
                .from(postTag)
                .innerJoin(tag).on(postTag.id.tagId.eq(tag.id))
                .where(
                        postTag.id.postId.in(postIds)
                )
                .fetch();
    }

    @Transactional
    @Override
    public void deleteByPostId(Long postId) {
        query.delete(postTag)
                .where(postTag.id.postId.eq(postId))
                .execute();
    }
}
