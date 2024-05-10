package com.example.springbootblogapi.domain.user;

import com.example.springbootblogapi.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private LocalDateTime deletedAt;
}
