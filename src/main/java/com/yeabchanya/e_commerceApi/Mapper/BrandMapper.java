package com.yeabchanya.e_commerceApi.Mapper;


import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.BrandResponse;
import com.yeabchanya.e_commerceApi.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    //Request → Entity (for saving to DB)
    Brand toEntity(BrandRequest request);

    //Entity → Response (for returning to API)
    BrandResponse toResponse(Brand brand);
}
