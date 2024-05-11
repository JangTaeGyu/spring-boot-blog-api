package com.example.springbootblogapi.domain.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    private final String secret;
    private final Long expirationDate;

    public JwtTokenGenerator(
            @Value("${app.jwt-secret}") String secret,
            @Value("${app.jwt-expiration-date}") Long expirationDate
    ) {
        this.secret = secret;
        this.expirationDate = expirationDate;
    }

    public Long expireTime(Date currentDate) {
        if (currentDate == null) currentDate = new Date();
        return currentDate.getTime() + expirationDate;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String create(String email) {
        Date currentDate = new Date();
        Date expireDate = new Date(expireTime(currentDate));

        return Jwts.builder()
                .claim("email", email)
                .setIssuer("BLOG")
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
}
