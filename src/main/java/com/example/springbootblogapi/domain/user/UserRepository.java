package com.example.springbootblogapi.domain.user;

import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    void create(User user);
}