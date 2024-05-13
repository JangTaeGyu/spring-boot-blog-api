package com.example.springbootblogapi.domain.user.dto;

import com.example.springbootblogapi.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDto {
    private final Long id;
    private final String email;
    private final String name;
    private final UserRole role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime latestAccessedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    @QueryProjection
    public UserDto(
            Long id,
            String email,
            String name,
            UserRole role,
            LocalDateTime latestAccessedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.latestAccessedAt = latestAccessedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
