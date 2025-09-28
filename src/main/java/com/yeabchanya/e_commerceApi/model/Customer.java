package com.yeabchanya.e_commerceApi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
}
