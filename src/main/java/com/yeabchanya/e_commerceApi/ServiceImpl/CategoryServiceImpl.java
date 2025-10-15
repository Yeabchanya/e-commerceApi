package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.Exception.ResourceAlreadyExistsException;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Util.Handler.CategoryServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.CategoryMapper;
import com.yeabchanya.e_commerceApi.Repository.CategoryRepository;
import com.yeabchanya.e_commerceApi.Service.CategoryService;
import com.yeabchanya.e_commerceApi.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryServiceUpdate serviceUpdate;

    private final CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryRequest request) {
        log.info("Creating category: {}", request.getName());

        if(existsByName(request.getName())){
            throw  new ResourceAlreadyExistsException("Category",request.getName());
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return categoryRepository.save(category);
    }


    @Override
    public Category updateCategory(Long id, CategoryRequest request) {
        log.info("Updating category id {}", id);

        Category category = getCategoryById(id);

        Category updated = serviceUpdate.updateDetails(category, request);
        categoryRepository.save(updated);

        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
    }

    @Override
    public List<Category> getAllCategories(Pageable pageable) {
        return List.of();
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
