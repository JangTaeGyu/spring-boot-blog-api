package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.comment.Comment;
import com.example.springbootblogapi.domain.comment.CommentRepository;
import com.example.springbootblogapi.domain.comment.QComment;
import com.example.springbootblogapi.domain.comment.data.CommentSearchData;
import com.example.springbootblogapi.domain.comment.dto.CommentDto;
import com.example.springbootblogapi.domain.comment.dto.PostCommentDto;
import com.example.springbootblogapi.domain.comment.dto.QCommentDto;
import com.example.springbootblogapi.domain.comment.dto.QPostCommentDto;
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

import static com.example.springbootblogapi.domain.comment.QComment.comment;
import static com.example.springbootblogapi.domain.post.QPost.post;
import static com.example.springbootblogapi.domain.user.QUser.user;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository commentRepository;
    private final JPAQueryFactory query;

    private QCommentDto selectFields(QComment parentComment) {
        return new QCommentDto(
                comment.id,
                comment.body,
                comment.show,
                comment.createdAt,
                comment.updatedAt,
                post.id,
                post.title,
                post.show,
                post.deletedAt,
                parentComment.id,
                parentComment.body,
                parentComment.show
        );
    }

    private QPostCommentDto selectPostCommentFields() {
        QComment replyComment = new QComment("replyComment");

        return new QPostCommentDto(
                comment.id,
                comment.body,
                comment.createdAt,
                comment.updatedAt,
                ExpressionUtils.as(
                        JPAExpressions.select(replyComment.count())
                                .from(replyComment)
                                .where(
                                        replyComment.show.eq(true),
                                        replyComment.parentId.eq(comment.id)
                                ), "countOfComments"
                ),
                user.id,
                user.email,
                user.name
        );
    }

    private BooleanBuilder toBooleanBuilder(CommentSearchData commentSearchData) {
        return new BooleanBuilder(eqShow(commentSearchData.getShow()))
                .and(containsKeyword(commentSearchData.getKeyword()));
    }

    @Override
    public Page<CommentDto> searchCommentsBy(CommentSearchData searchData, Pageable pageable) {
        QComment parentComment = new QComment("parentComment");

        BooleanBuilder booleanBuilder = toBooleanBuilder(searchData);

        List<CommentDto> content = query.select(selectFields(parentComment))
                .from(comment)
                .innerJoin(post).on(comment.postId.eq(post.id))
                .leftJoin(parentComment).on(comment.parentId.eq(parentComment.id))
                .where(booleanBuilder)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(post.createdAt.desc())
                .fetch();

        JPAQuery<Long> count = query.select(post.count())
                .from(post)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public List<PostCommentDto> findAllPostCommentsByPostId(Long postId) {
        return query.select(selectPostCommentFields())
                .from(comment)
                .innerJoin(user).on(comment.userId.eq(user.id))
                .where(
                        comment.parentId.isNull(),
                        eqShow(true),
                        eqPostId(postId)
                )
                .orderBy(comment.createdAt.desc())
                .fetch();
    }

    @Override
    public List<PostCommentDto> findAllPostCommentsByPostIdAndParentId(Long postId, Long parentId) {
        return query.select(selectPostCommentFields())
                .from(comment)
                .innerJoin(user).on(comment.userId.eq(user.id))
                .where(
                        eqShow(true),
                        eqPostId(postId),
                        eqParentId(parentId)
                )
                .orderBy(comment.createdAt.desc())
                .fetch();
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public Optional<Comment> findByIdAndPostId(Long commentId, Long postId) {
        return commentRepository.findByIdAndPostId(commentId, postId);
    }

    @Transactional
    @Override
    public void create(Comment comment) {
        commentRepository.save(comment);
    }

    public BooleanExpression eqShow(Boolean show) {
        if (show == null) return null;
        return comment.show.eq(show);
    }

    public BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        return comment.body.contains(keyword);
    }

    public BooleanExpression eqPostId(Long postId) {
        if (postId == null) return null;
        return comment.postId.eq(postId);
    }

    public BooleanExpression eqParentId(Long parentId) {
        if (parentId == null) return null;
        return comment.parentId.eq(parentId);
    }
}
