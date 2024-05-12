package com.example.springbootblogapi.domain.user.data;

import com.example.springbootblogapi.domain.user.exception.NotMatchPasswordException;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class UserUpdateData {
    private final String password;
    private final String confirmPassword;
    private final String name;

    public UserUpdateData(String password, String confirmPassword, String name) {
        Assert.notNull(name, "name not null");

        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
    }

    public boolean hasPassword() {
        return password != null && password.isBlank() && confirmPassword != null && confirmPassword.isBlank();
    }

    public void checkMatchPassword() {
        if (!password.equals(confirmPassword)) {
            throw new NotMatchPasswordException();
        }
    }
}
