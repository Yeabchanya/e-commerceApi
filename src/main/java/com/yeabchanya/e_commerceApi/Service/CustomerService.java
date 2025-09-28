package com.yeabchanya.e_commerceApi.Service;

import com.yeabchanya.e_commerceApi.Dto.Request.CustomerRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.CustomerResponse;
import com.yeabchanya.e_commerceApi.model.Customer;

import java.util.List;

public interface CustomerService {
	
	Customer create(CustomerRequest request);
	
	Customer getCustomerById(Long Id);

	Customer update (Long id, CustomerRequest request);
	
	List<CustomerResponse> getCustomers();
	
	Customer delete (long id);

}