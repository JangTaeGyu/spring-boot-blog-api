package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserCreateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreator userCreator;

    public Long createUser(UserCreateData data) {
        return userCreator.createUser(data);
    }
}
