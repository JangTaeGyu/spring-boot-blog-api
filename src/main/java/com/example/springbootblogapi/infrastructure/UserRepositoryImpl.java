package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.user.User;
import com.example.springbootblogapi.domain.user.UserRepository;
import com.example.springbootblogapi.domain.user.UserRole;
import com.example.springbootblogapi.domain.user.data.UserSearchData;
import com.example.springbootblogapi.domain.user.dto.QUserDto;
import com.example.springbootblogapi.domain.user.dto.UserDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.springbootblogapi.domain.user.QUser.user;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;
    private final JPAQueryFactory query;

    private BooleanBuilder toBooleanBuilder(UserSearchData searchData) {
        return new BooleanBuilder(user.deletedAt.isNull())
                .and(containsKeyword(searchData.getKeyword()))
                .and(eqRole(searchData.getRole()));
    }

    @Override
    public Page<UserDto> searchBy(UserSearchData searchData, Pageable pageable) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(searchData);

        List<UserDto> content = query.select(new QUserDto(
                        user.id,
                        user.email,
                        user.name,
                        user.latestAccessedAt,
                        user.createdAt,
                        user.updatedAt
                ))
                .from(user)
                .where(booleanBuilder)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(user.createdAt.desc())
                .fetch();

        JPAQuery<Long> count = query.select(user.count())
                .from(user)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public Long create(User user) {
        return userRepository.save(user).getId();
    }

    @Transactional
    @Override
    public void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt) {
        query.update(user)
                .set(user.latestAccessedAt, latestAccessedAt)
                .where(
                        user.email.eq(email),
                        user.deletedAt.isNull()
                )
                .execute();
    }

    public BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        return user.email.contains(keyword).or(user.name.contains(keyword));
    }

    public BooleanExpression eqRole(UserRole role) {
        if (role == null) return null;
        return user.role.eq(role);
    }
}
