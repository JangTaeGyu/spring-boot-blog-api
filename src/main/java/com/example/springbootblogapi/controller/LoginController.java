package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.request.LoginRequest;
import com.example.springbootblogapi.domain.user.LoginService;
import com.example.springbootblogapi.domain.user.dto.AccessTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        AccessTokenDto token = loginService.login(request.toData());
        return ResponseEntity.ok(token);
    }
}
