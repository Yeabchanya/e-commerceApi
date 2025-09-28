package com.yeabchanya.e_commerceApi.Dto.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserResponse {

    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    private Date dateOfBirth;

}
