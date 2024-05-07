package com.example.springbootblogapi.domain.comment;

import com.example.springbootblogapi.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public void changeShow(boolean show) {
        this.show = show;
    }
}
