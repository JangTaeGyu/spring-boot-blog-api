package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaUserRepository extends Repository<User, Long> {
    boolean existsByEmail(String email);

    @Query(value = "select u from User u where u.email = :email and u.deletedAt is null")
    Optional<User> findByEmail(String email);

    @Query(value = "select u from User u where u.id = :id and u.deletedAt is null")
    Optional<User> findById(Long id);

    User save(User user);
}
