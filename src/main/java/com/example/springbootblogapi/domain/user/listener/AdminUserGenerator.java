package com.example.springbootblogapi.domain.user.listener;

import com.example.springbootblogapi.domain.user.User;
import com.example.springbootblogapi.domain.user.UserRepository;
import com.example.springbootblogapi.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserGenerator implements ApplicationListener<ApplicationStartedEvent> {
    private final String adminEmail;
    private final String adminPassword;
    private final String adminName;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserGenerator(
            @Value("${admin.email}") String adminEmail,
            @Value("${admin.password}") String adminPassword,
            @Value("${admin.name}") String adminName,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        boolean result = userRepository.existsByEmail(adminEmail);
        if (!result) {
            User user = new User(adminEmail, passwordEncoder.encode(adminPassword), adminName, UserRole.ADMIN);
            userRepository.create(user);
        }
    }
}
