package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserCreateData;
import com.example.springbootblogapi.domain.user.data.UserUpdateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreator userCreator;
    private final UserUpdater userUpdater;

    public Long createUser(UserCreateData data) {
        return userCreator.createUser(data);
    }

    public void updateUser(Long userId, UserUpdateData data) {
        userUpdater.updateUser(userId, data);
    }
}
