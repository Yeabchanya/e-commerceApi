package com.yeabchanya.e_commerceApi.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Setter
@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {
	private final HttpStatus status;
	private final String message;
}