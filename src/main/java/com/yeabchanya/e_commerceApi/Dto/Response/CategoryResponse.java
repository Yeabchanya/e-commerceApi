package com.yeabchanya.e_commerceApi.Dto.Response;

import com.yeabchanya.e_commerceApi.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponse {

    private Long id;

    private String name;

    private int totalProducts;

}
