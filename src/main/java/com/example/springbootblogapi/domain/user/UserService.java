package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserCreateData;
import com.example.springbootblogapi.domain.user.data.UserSearchData;
import com.example.springbootblogapi.domain.user.data.UserUpdateData;
import com.example.springbootblogapi.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserQuery userQuery;
    private final UserCreator userCreator;
    private final UserUpdater userUpdater;

    public Page<UserDto> searchUsersBy(UserSearchData searchData, Pageable pageable) {
        return userQuery.searchUsersBy(searchData, pageable);
    }

    public Long createUser(UserCreateData data) {
        return userCreator.createUser(data);
    }

    public void updateUser(Long userId, UserUpdateData data) {
        userUpdater.updateUser(userId, data);
    }
}
