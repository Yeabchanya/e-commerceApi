package com.yeabchanya.e_commerceApi.Mapper;


import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.ProductResponse;
import com.yeabchanya.e_commerceApi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    //Request → Entity (for saving to DB)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toEntity(ProductRequest request);

    //Entity → Response (for returning to API)
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "brand.id", target = "brandId")
    ProductResponse toResponse(Product product);
}
