package com.example.springbootblogapi.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
