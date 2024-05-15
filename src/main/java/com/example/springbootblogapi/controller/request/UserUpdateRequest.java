package com.example.springbootblogapi.controller.request;

import com.example.springbootblogapi.domain.user.data.UserUpdateData;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class UserUpdateRequest {
    @Min(5)
    private String password;
    private String confirmPassword;

    @NotBlank
    private String name;

    public UserUpdateData toData() {
        return new UserUpdateData(password, confirmPassword, name);
    }
}
