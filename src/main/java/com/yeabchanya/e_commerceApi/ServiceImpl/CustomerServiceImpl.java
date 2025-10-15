package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.CustomerRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.CustomerResponse;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Util.Handler.CustomerServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.CustomerMapper;
import com.yeabchanya.e_commerceApi.Repository.CustomerRepository;
import com.yeabchanya.e_commerceApi.Service.CustomerService;
import com.yeabchanya.e_commerceApi.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceUpdate serviceUpdate;

    private final CustomerMapper customerMapper;

    @Override
    public Customer create(CustomerRequest request) {
        return customerRepository.save(customerMapper.toEntity(request));
    }

    @Override
    public Customer getCustomerById(Long Id) {
        return customerRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", Id));
    }

    @Override
    public Customer update(Long id, CustomerRequest request) {
        Customer customer = getCustomerById(id);
        serviceUpdate.updateDetails(customer, request);
        return customer;
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll()
                .stream().map(customerMapper::toResponse)
                .toList();
    }

    @Override
    public Customer delete(long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        return customer;
    }

}