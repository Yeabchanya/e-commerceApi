package com.yeabchanya.e_commerceApi.Dto.Response;

import com.yeabchanya.e_commerceApi.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long ProductId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
    private Long brandId;

}
