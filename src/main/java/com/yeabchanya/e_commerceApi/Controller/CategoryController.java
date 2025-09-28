package com.yeabchanya.e_commerceApi.Controller;

import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequest request) {
        log.info("POST /api/category called");
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

}
