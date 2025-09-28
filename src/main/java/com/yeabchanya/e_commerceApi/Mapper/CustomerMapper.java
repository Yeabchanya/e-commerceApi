package com.yeabchanya.e_commerceApi.Mapper;


import com.yeabchanya.e_commerceApi.Dto.Request.CustomerRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.CustomerResponse;
import com.yeabchanya.e_commerceApi.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    //Request → Entity (for saving to DB)
    Customer toEntity(CustomerRequest request);

    //Entity → Response (for returning to API)
    CustomerResponse toResponse(Customer customer);
}
