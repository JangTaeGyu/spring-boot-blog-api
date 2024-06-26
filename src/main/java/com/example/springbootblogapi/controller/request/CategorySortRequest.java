package com.example.springbootblogapi.controller.request;

import com.example.springbootblogapi.domain.category.data.CategorySortData;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class CategorySortRequest {
    @NotEmpty
    private List<Long> ids;

    public CategorySortData toData() {
        return new CategorySortData(ids);
    }
}
