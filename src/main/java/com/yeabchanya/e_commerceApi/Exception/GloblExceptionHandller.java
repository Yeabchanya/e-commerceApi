package com.yeabchanya.e_commerceApi.Exception;

import com.yeabchanya.e_commerceApi.Exception.ExceptionResponse.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GloblExceptionHandller {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handApiException(ApiException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());

        return ResponseEntity
                .status(e.getStatus())
                .body(errorResponse);
    }

}