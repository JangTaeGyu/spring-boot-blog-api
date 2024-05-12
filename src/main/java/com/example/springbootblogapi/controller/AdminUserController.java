package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.request.UserCreateRequest;
import com.example.springbootblogapi.controller.response.CreatedResponse;
import com.example.springbootblogapi.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid UserCreateRequest request) {
        Long userId = userService.createUser(request.toData());
        CreatedResponse response = new CreatedResponse(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
