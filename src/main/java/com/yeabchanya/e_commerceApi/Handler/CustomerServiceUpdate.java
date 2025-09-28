package com.yeabchanya.e_commerceApi.Handler;

import com.yeabchanya.e_commerceApi.Dto.Request.CustomerRequest;
import com.yeabchanya.e_commerceApi.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceUpdate {

    public void updateDetails(Customer customer, CustomerRequest request) {

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setGender(request.getGender());
        customer.setDateOfBirth(request.getDateOfBirth());

    }

}
