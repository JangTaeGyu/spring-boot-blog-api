package com.example.springbootblogapi.domain.user.data;

import com.example.springbootblogapi.domain.user.UserRole;
import lombok.Getter;

@Getter
public class LoggedInUserData {
    private final Long id;
    private final String email;
    private final String name;
    private final UserRole role;

    public LoggedInUserData(Long id, String email, String name, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
