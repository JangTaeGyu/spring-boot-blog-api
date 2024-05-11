package com.example.springbootblogapi.controller.request;

import com.example.springbootblogapi.domain.user.data.LoginData;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public LoginData toData() {
        return new LoginData(email, password);
    }
}
