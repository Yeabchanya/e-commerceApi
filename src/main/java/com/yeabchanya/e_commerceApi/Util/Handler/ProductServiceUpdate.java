package com.yeabchanya.e_commerceApi.Util.Handler;

import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Repository.ProductRepository;
import com.yeabchanya.e_commerceApi.model.Brand;
import com.yeabchanya.e_commerceApi.model.Category;
import com.yeabchanya.e_commerceApi.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceUpdate {
    public Product updateDetails(Product product, ProductRequest request, Brand brand, Category category) {

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        product.setBrand(brand);
        product.setCategory(category);

        return product;
    }


}
