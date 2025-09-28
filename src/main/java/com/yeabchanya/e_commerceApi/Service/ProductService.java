package com.yeabchanya.e_commerceApi.Service;


import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.ProductResponse;
import com.yeabchanya.e_commerceApi.model.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    Product getProductById(Long id);

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    Product deleteProduct(Long id);

    Page<ProductResponse> searchProducts(String keyword,
                                         Long categoryId,
                                         Long brandId,
                                         BigDecimal minPrice,
                                         BigDecimal maxPrice,
                                         Boolean inStock,
                                         int page,
                                         int size,
                                         String sortBy,
                                         String sortDir);

    List<ProductResponse> filterProducts(Long categoryId, Long brandId);

}
