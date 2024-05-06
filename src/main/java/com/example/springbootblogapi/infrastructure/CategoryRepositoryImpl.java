package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.category.Category;
import com.example.springbootblogapi.domain.category.CategoryDto;
import com.example.springbootblogapi.domain.category.CategoryRepository;
import com.example.springbootblogapi.domain.category.QCategoryDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.springbootblogapi.domain.category.QCategory.category;
import static com.example.springbootblogapi.domain.post.QPost.post;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository categoryRepository;
    private final JPAQueryFactory query;

    @Override
    public List<CategoryDto> findAllSortedAscOrder() {
        return query.select(new QCategoryDto(
                        category.id,
                        category.name,
                        category.description,
                        category.show,
                        category.sort,
                        category.createdAt,
                        category.updatedAt,
                        ExpressionUtils.as(
                                JPAExpressions.select(post.count())
                                        .from(post)
                                        .where(
                                                post.deletedAt.isNull(),
                                                post.categoryId.eq(category.id)
                                        ), "countOfPosts")
                ))
                .from(category)
                .orderBy(category.sort.asc())
                .fetch();
    }

    @Transactional
    @Override
    public Long create(Category category) {
        category.setSort(maxSort() + 1);
        return categoryRepository.save(category).getId();
    }

    private Integer maxSort() {
        Integer maxSort = categoryRepository.getMaxSort();
        return maxSort == null ? 0 : maxSort;
    }

    @Override
    public boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Long getCountByIds(List<Long> categoryIds) {
        return query.select(category.count())
                .from(category)
                .where(
                        category.id.in(categoryIds)
                )
                .fetchOne();
    }

    @Transactional
    @Override
    public void updateSortById(Integer sort, Long categoryId) {
        query.update(category)
                .set(category.sort, sort)
                .where(
                        category.id.eq(categoryId)
                )
                .execute();
    }

    @Transactional
    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
