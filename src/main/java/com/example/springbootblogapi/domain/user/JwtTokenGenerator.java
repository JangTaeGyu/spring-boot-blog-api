package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.support.exception.HttpException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    private static final String BLOG_ISSUER = "BLOG";

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
                .setIssuer(BLOG_ISSUER)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .requireIssuer(BLOG_ISSUER)
                    .build()
                    .parse(token);

            return true;
        } catch (SignatureException e) {
            throw new HttpException("Invalid Jwt Signature", HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            throw new HttpException("Invalid Jwt Token", HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException e) {
            throw new HttpException("Expired Jwt Token", HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            throw new HttpException("Unsupported Jwt Token", HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            throw new HttpException("Jwt claims String Is Empty", HttpStatus.UNAUTHORIZED);
        } catch (IncorrectClaimException e) {
            throw new HttpException("Invalid Jwt Claim", HttpStatus.UNAUTHORIZED);
        }
    }

    public String getClaimTarget(String token, String target){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(target)
                .toString();
    }
}
