package com.yeabchanya.e_commerceApi.Service;


import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.model.Category;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories(Pageable pageable);
    Category createCategory(CategoryRequest request);
    Category updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    boolean existsByName(String name);
}
