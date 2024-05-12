package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserCreateData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(UserCreateData data) {
        data.checkMatchPassword();
        User user = data.toEntity(passwordEncoder.encode(data.getPassword()), UserRole.ADMIN);
        return userRepository.create(user);
    }
}
