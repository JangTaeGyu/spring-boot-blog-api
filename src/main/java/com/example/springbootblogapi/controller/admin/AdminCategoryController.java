package com.example.springbootblogapi.controller.admin;

import com.example.springbootblogapi.controller.admin.request.CategoryInputRequest;
import com.example.springbootblogapi.controller.admin.request.CategorySortRequest;
import com.example.springbootblogapi.domain.category.CategoryDto;
import com.example.springbootblogapi.domain.category.CategoryService;
import com.example.springbootblogapi.support.response.CreatedResponse;
import com.example.springbootblogapi.support.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> index() {
        List<CategoryDto> categories = categoryService.getAllSortedCategories();
        SuccessfulResponse<List<CategoryDto>> response = new SuccessfulResponse<>(categories);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid CategoryInputRequest request) {
        Long categoryId = categoryService.createCategory(request.toData());
        CreatedResponse response = new CreatedResponse(categoryId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> show(@PathVariable Long categoryId) {
        CategoryDto category = categoryService.getCategoryById(categoryId);
        SuccessfulResponse<CategoryDto> response = new SuccessfulResponse<>(category);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> update(@PathVariable Long categoryId, @RequestBody @Valid CategoryInputRequest request) {
        categoryService.updateCategoryById(categoryId, request.toData());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{categoryId}/enable")
    public ResponseEntity<Void> enable(@PathVariable Long categoryId) {
        categoryService.setCategoryVisibility(categoryId, true);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{categoryId}/disable")
    public ResponseEntity<Void> disable(@PathVariable Long categoryId) {
        categoryService.setCategoryVisibility(categoryId, false);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/sort")
    public ResponseEntity<Void> sort(@RequestBody @Valid CategorySortRequest request) {
        categoryService.sortCategories(request.toData());
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
