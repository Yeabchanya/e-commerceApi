package com.yeabchanya.e_commerceApi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private Date dateOfBirth;

}
