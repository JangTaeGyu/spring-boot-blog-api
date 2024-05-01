package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @ColumnDefault("'true'")
    private boolean show;

    @Setter
    private Integer sort;

    public Category(String name, String description, boolean show) {
        this.name = name;
        this.description = description;
        this.show = show;
    }
}