package com.yeabchanya.e_commerceApi.Controller;

import com.yeabchanya.e_commerceApi.Dto.Request.CustomerRequest;
import com.yeabchanya.e_commerceApi.Mapper.CustomerMapper;
import com.yeabchanya.e_commerceApi.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerMapper.toResponse(customerService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerMapper.toResponse(customerService.getCustomerById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerMapper.toResponse(customerService.update(id, request)));
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.delete(id));
    }

}