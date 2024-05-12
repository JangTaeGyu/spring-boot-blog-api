package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.user.User;
import com.example.springbootblogapi.domain.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.springbootblogapi.domain.user.QUser.user;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;
    private final JPAQueryFactory query;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
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
}
