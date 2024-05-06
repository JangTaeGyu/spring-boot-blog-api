package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.BaseEntity;
import com.example.springbootblogapi.domain.post.data.PostData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    @ColumnDefault("'false'")
    private boolean show;

    protected LocalDateTime deletedAt;

    public Post(Long categoryId, String title, String body, boolean show) {
        this.categoryId = categoryId;
        this.title = title;
        this.body = body;
        this.show = show;
    }

    public static Post fakeEntity(Long id) {
        Post post = new Post();
        post.id = id;
        return  post;
    }

    public void update(PostData postData) {
        this.categoryId = postData.getCategoryId();
        this.title = postData.getTitle();
        this.body = postData.getBody();
    }
}
