package com.yeabchanya.e_commerceApi.Exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends ApiException {
    public ResourceAlreadyExistsException(String message, String name) {
        super(HttpStatus.CONFLICT, String.format("%s already exists: %s", message, name));
    }
}