package com.yeabchanya.e_commerceApi.Repository;

import com.yeabchanya.e_commerceApi.model.Customer;
import com.yeabchanya.e_commerceApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}