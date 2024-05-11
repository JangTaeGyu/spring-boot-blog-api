package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.LoginData;
import com.example.springbootblogapi.domain.user.dto.AccessTokenDto;
import com.example.springbootblogapi.domain.user.exception.AuthenticationFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    private AccessTokenDto makeAccessTokenDto(Authentication authenticate) {
        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        String token = jwtTokenGenerator.create(userDetails.getUsername());

        return new AccessTokenDto(token, jwtTokenGenerator.expireTime(null));
    }

    public AccessTokenDto login(LoginData request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);

            return makeAccessTokenDto(authenticate);
        } catch (AuthenticationException e) {
            throw new AuthenticationFailureException();
        }
    }
}
