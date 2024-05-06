package com.example.springbootblogapi.domain.post;

import com.example.springbootblogapi.domain.category.CategoryQuery;
import com.example.springbootblogapi.domain.post.data.PostData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostValidation {
    private final CategoryQuery categoryQuery;

    public void validate(PostData postData) {
        categoryQuery.checkCategory(postData.getCategoryId());
    }
}
