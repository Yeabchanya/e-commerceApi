package com.yeabchanya.e_commerceApi.Controller;

import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.ProductResponse;
import com.yeabchanya.e_commerceApi.Mapper.ProductMapper;
import com.yeabchanya.e_commerceApi.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        log.info("POST /api/create products called");
        final ProductResponse product = productService.createProduct(request);
        log.info(String.valueOf(product.getCategoryId()));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        final ProductResponse response = productMapper.toResponse(productService.getProductById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productMapper.toResponse(productService.deleteProduct(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> brandUpdate(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        log.info("GET /api/update called");
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //GET /api/products/search?keyword=iphone&categoryId=3&minPrice=1000&maxPrice=1500&page=0&size=5&sortBy=price&sortDir=desc
    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam(required = false) String keyword,
                                            @RequestParam(required = false) Long categoryId,
                                            @RequestParam(required = false) Long brandId,
                                            @RequestParam(required = false) BigDecimal minPrice,
                                            @RequestParam(required = false) BigDecimal maxPrice,
                                            @RequestParam(required = false) Boolean inStock,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "name") String sortBy,
                                            @RequestParam(defaultValue = "asc") String sortDir) {

        log.info("POST /api/search called");
        Page<ProductResponse> productResponses = productService.searchProducts(keyword, categoryId, brandId, minPrice, maxPrice,
                inStock, page, size, sortBy, sortDir);

        return ResponseEntity.ok(productResponses);
    }

    // GET endpoint to filter products by category and/or brand

    //GET /api/products/filter?categoryId=3
    //GET /api/products/filter?brandId=2
    //GET /api/products/filter?categoryId=3&brandId=2
    //GET /api/products/filter
    @GetMapping("/filter")
    public ResponseEntity<?> filterProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId) {

        return ResponseEntity.ok(productService.filterProducts(categoryId, brandId));
    }

}
