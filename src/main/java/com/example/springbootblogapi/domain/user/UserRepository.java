package com.example.springbootblogapi.domain.user;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);
    Long create(User user);
    void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt);
}
