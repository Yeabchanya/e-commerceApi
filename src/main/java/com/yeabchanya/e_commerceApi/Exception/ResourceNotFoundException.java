package com.yeabchanya.e_commerceApi.Exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d not found", resourceName, id));
    }

}