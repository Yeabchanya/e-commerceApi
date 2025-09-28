package com.yeabchanya.e_commerceApi.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private  HttpStatus status;
	private  String message;

}