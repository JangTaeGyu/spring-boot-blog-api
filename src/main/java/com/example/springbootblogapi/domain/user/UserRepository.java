package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserSearchData;
import com.example.springbootblogapi.domain.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository {
    Page<UserDto> searchBy(UserSearchData searchData, Pageable pageable);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);
    Long create(User user);
    void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt);
}
