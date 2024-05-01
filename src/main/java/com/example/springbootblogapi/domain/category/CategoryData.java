package com.example.springbootblogapi.domain.category;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public record CategoryData(String name, String description) {
    public CategoryData {
        Assert.notNull(name, "name not null");
        Assert.notNull(description, "description not null");
    }

    public Category toEntity() {
        return new Category(this.name, this.description, true);
    }
}
