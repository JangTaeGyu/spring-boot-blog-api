package com.example.springbootblogapi.controller.request;

import com.example.springbootblogapi.domain.user.data.UserCreateData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserCreateRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String name;

    public UserCreateData toData() {
        return new UserCreateData(email, password, confirmPassword, name);
    }
}
