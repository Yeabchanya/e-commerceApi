package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.LoginRequest;
import com.yeabchanya.e_commerceApi.Dto.Request.UserRequest;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Mapper.UserMapper;
import com.yeabchanya.e_commerceApi.Repository.UserRepository;
import com.yeabchanya.e_commerceApi.Service.UserService;
import com.yeabchanya.e_commerceApi.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User register(UserRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }

        return userRepository.save(userMapper.toEntity(request));
    }

    @Override
    public String login(LoginRequest request) {
        return "";
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public User updateUser(Long id, UserRequest request) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
        return user;
    }
}
