package com.yeabchanya.e_commerceApi.Handler;

import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceUpdate {

    public Category updateDetails(Category category, CategoryRequest request) {

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;
    }


}
