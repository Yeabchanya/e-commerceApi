package com.yeabchanya.e_commerceApi.Mapper;


import com.yeabchanya.e_commerceApi.Dto.Request.UserRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.UserResponse;
import com.yeabchanya.e_commerceApi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //Request → Entity (for saving to DB)
    User toEntity(UserRequest request);

    //Entity → Response (for returning to API)
    UserResponse toResponse(User user);
}
