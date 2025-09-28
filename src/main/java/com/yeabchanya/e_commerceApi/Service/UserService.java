package com.yeabchanya.e_commerceApi.Service;

import com.yeabchanya.e_commerceApi.Dto.Request.LoginRequest;
import com.yeabchanya.e_commerceApi.Dto.Request.UserRequest;
import com.yeabchanya.e_commerceApi.model.User;

public interface UserService {
    User register(UserRequest request);
    String login(LoginRequest request);
    User getUserById(Long id);
    User updateUser(Long id, UserRequest request);
    User deleteUser(Long id);


}
