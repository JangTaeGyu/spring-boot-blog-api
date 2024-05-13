package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserSearchData;
import com.example.springbootblogapi.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQuery {
    private final UserRepository userRepository;

    public Page<UserDto> searchUsersBy(UserSearchData searchData, Pageable pageable) {
        return userRepository.searchBy(searchData, pageable);
    }
}
