package com.example.springbootblogapi.domain.category;

import com.example.springbootblogapi.domain.BaseEntity;
import com.example.springbootblogapi.domain.category.data.CategoryData;
import com.example.springbootblogapi.domain.category.dto.CategoryDto;
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

    public static Category fakeEntity(Long id) {
        Category category = new Category();
        category.id = id;
        return  category;
    }

    public CategoryDto toDto() {
        return new CategoryDto(this.id, this.name, this.description, this.show, this.sort, this.createdAt, this.updatedAt);
    }

    public void update(CategoryData data) {
        this.name = data.getName();
        this.description = data.getDescription();
    }

    public void changeShow(boolean show) {
        this.show = show;
    }
}
