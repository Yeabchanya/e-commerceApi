package com.yeabchanya.e_commerceApi.Controller;

import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.CategoryResponse;
import com.yeabchanya.e_commerceApi.Mapper.CategoryMapper;
import com.yeabchanya.e_commerceApi.Service.CategoryService;
import com.yeabchanya.e_commerceApi.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {

        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> list(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        // @TODO
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {

        Category category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
                                                   @RequestBody CategoryRequest request) {

        Category category = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}