package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Handler.CategoryServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.CategoryMapper;
import com.yeabchanya.e_commerceApi.Repository.CategoryRepository;
import com.yeabchanya.e_commerceApi.Service.CategoryService;
import com.yeabchanya.e_commerceApi.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryServiceUpdate serviceUpdate;

    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category createCategory(CategoryRequest request) {
        return categoryRepository.save(categoryMapper.toEntity(request));
    }


    @Override
    public Category updateCategory(Long id, CategoryRequest request) {
        Category category = getCategoryById(id);
        serviceUpdate.updateDetails(category, request);
        return category;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
    }
}
