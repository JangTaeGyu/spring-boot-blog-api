package com.example.springbootblogapi.domain.category.data;

import com.example.springbootblogapi.domain.category.Category;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class CategoryData {
    private final String name;
    private final String description;

    public CategoryData(String name, String description) {
        Assert.notNull(name, "name not null");
        Assert.notNull(description, "description not null");

        this.name = name;
        this.description = description;
    }

    public Category toEntity() {
        return new Category(this.name, this.description, true);
    }
}
