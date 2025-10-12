package com.yeabchanya.e_commerceApi.Exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {
    public BadRequestException(String message, Long id) {
        super(HttpStatus.BAD_REQUEST, String.format("%s with id = %d has no logo to delete", message, id));
    }
}