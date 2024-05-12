package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.user.data.UserUpdateData;
import com.example.springbootblogapi.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateUser(Long userId, UserUpdateData data) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("userId", userId));
        if (data.hasPassword()) {
            data.checkMatchPassword();
            user.updatePassword(passwordEncoder.encode(data.getPassword()));
        }

        user.updateName(data.getName());
    }
}
