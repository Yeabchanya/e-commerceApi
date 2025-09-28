package com.yeabchanya.e_commerceApi.Dto.Request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
}
