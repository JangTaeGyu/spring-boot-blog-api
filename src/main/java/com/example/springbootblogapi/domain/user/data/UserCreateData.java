package com.example.springbootblogapi.domain.user.data;

import com.example.springbootblogapi.domain.user.User;
import com.example.springbootblogapi.domain.user.UserRole;
import com.example.springbootblogapi.domain.user.exception.NotMatchPasswordException;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class UserCreateData {
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final String name;

    public UserCreateData(String email, String password, String confirmPassword, String name) {
        Assert.notNull(email, "email not null");
        Assert.notNull(password, "password not null");
        Assert.notNull(confirmPassword, "confirmPassword not null");
        Assert.notNull(name, "name not null");

        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
    }

    public void checkMatchPassword() {
        if (!password.equals(confirmPassword)) {
            throw new NotMatchPasswordException();
        }
    }

    public User toEntity(String encodedPassword, UserRole role) {
        return new User(email, encodedPassword, name, role);
    }
}
