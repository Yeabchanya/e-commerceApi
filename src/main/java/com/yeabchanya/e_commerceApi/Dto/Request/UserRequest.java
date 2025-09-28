package com.yeabchanya.e_commerceApi.Dto.Request;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private Date dateOfBirth;

}
