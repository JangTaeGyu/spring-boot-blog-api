package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.BaseEntity;
import com.example.springbootblogapi.domain.comment.exception.UnauthorizedUserException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Column(columnDefinition="TEXT")
    private String body;

    @ColumnDefault("'true'")
    private boolean show;

    @Column(nullable = false)
    private Long postId;

    private Long parentId;
    private Long userId;
    private LocalDateTime deletedAt;

    public Comment(String body, Long postId, Long parentId, Long userId) {
        this.body = body;
        this.show = true;
        this.postId = postId;
        this.parentId = parentId;
        this.userId = userId;
    }

    public void changeShow(boolean show) {
        this.show = show;
    }

    public void update(String body) {
        this.body = body;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void verifyWriter(Long userId) {
        if (!this.getUserId().equals(userId)) {
            throw new UnauthorizedUserException();
        }
    }
}
