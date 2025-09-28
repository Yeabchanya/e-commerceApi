package com.yeabchanya.e_commerceApi.Mapper;


import com.yeabchanya.e_commerceApi.Dto.Request.CategoryRequest;
import com.yeabchanya.e_commerceApi.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //Request → Entity (for saving to DB)
    Category toEntity(CategoryRequest request);

    //Entity → Response (for returning to API)
    CategoryRequest toResponse(Category category);
}
