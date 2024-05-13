package com.example.springbootblogapi.domain.user.data;

import com.example.springbootblogapi.domain.user.UserRole;
import lombok.Getter;

@Getter
public class UserSearchData {
    private final String keyword;
    private final UserRole role;

    public UserSearchData(String keyword, UserRole role) {
        this.keyword = keyword;
        this.role = role;
    }
}
