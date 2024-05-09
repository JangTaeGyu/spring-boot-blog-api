package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.*;
import com.example.springbootblogapi.domain.post.data.PostSearchData;
import com.example.springbootblogapi.domain.post.dto.PostDto;
import com.example.springbootblogapi.domain.post.dto.QPostDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.springbootblogapi.domain.category.QCategory.category;
import static com.example.springbootblogapi.domain.post.QPost.post;
import static com.example.springbootblogapi.domain.comment.QComment.comment;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository postRepository;
    private final JPAQueryFactory query;

    private QPostDto selectFields() {
        return new QPostDto(
                post.id,
                post.title,
                post.body,
                post.show,
                post.createdAt,
                post.updatedAt,
                ExpressionUtils.as(
                        JPAExpressions.select(comment.count())
                                .from(comment)
                                .where(
                                        comment.postId.eq(post.id)
                                ), "countOfComments"),
                category.id,
                category.name
        );
    }

    private BooleanBuilder toBooleanBuilder(PostSearchData searchData) {
        return new BooleanBuilder(post.deletedAt.isNull())
                .and(eqCategoryId(searchData.getCategoryId()))
                .and(eqShow(searchData.getShow()))
                .and(containsKeyword(searchData.getKeyword()));
    }

    @Override
    public Page<PostDto> searchBy(PostSearchData searchData, Pageable pageable) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(searchData);

        List<PostDto> content = query.select(selectFields())
                .from(post)
                .innerJoin(category).on(post.categoryId.eq(category.id))
                .where(booleanBuilder)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(post.createdAt.desc())
                .fetch();

        JPAQuery<Long> count = query.select(post.count())
                .from(post)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Transactional
    @Override
    public Long create(Post post) {
        return postRepository.save(post).getId();
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Optional<PostDto> findPostById(Long postId) {
        return Optional.ofNullable(
                query.select(selectFields())
                        .from(post)
                        .innerJoin(category).on(post.categoryId.eq(category.id))
                        .where(
                                post.id.eq(postId),
                                post.deletedAt.isNull()
                        )
                        .fetchFirst()
        );
    }

    public BooleanExpression eqCategoryId(Long categoryId) {
        if (categoryId == null) return null;
        return post.categoryId.eq(categoryId);
    }

    public BooleanExpression eqShow(Boolean show) {
        if (show == null) return null;
        return post.show.eq(show);
    }

    public BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        return post.title.contains(keyword).or(post.body.contains(keyword));
    }
}
