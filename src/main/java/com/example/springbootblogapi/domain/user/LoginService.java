package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.LoginData;
import com.example.springbootblogapi.domain.user.dto.AccessTokenDto;
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

    public AccessTokenDto login(LoginData request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);

            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();

            System.out.println(userDetails.getUsername());
            // TODO 02. JWT 생성 - Payload 에 이메일과 만료 시간 추가 후 생성 하기
            // TODO 03. 토큰을 Dto 로 반환 하기

            return null;
        } catch (AuthenticationException e) {
            System.out.printf(e.getMessage());
            throw e;
        }
    }
}
