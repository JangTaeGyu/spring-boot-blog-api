package com.example.springbootblogapi.domain.user.dto;

import lombok.Getter;

@Getter
public class AccessTokenDto {
    private final String tokenType = "Bearer";
    private final String accessToken;
    private final Long expireTime;

    public AccessTokenDto(String accessToken, Long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }
}
