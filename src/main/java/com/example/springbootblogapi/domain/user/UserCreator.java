package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserCreateData;
import com.example.springbootblogapi.domain.user.exception.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(UserCreateData data) {
        boolean result = userRepository.existsByEmail(data.getEmail());
        if (result) {
            throw new DuplicateEmailException();
        }

        data.checkMatchPassword();
        User user = data.toEntity(passwordEncoder.encode(data.getPassword()));
        return userRepository.create(user);
    }
}
