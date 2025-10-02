package com.yeabchanya.e_commerceApi.Service;


import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.ProductResponse;
import com.yeabchanya.e_commerceApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);
    Product getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
    Product deleteProduct(Long id);
    void softDeleteProduct(Long productId, String deletedBy);
    void restoreProduct(Long productId);
    List<ProductResponse> getAllProducts();
    // Pagination & Filtering
    Page<Product> listAllProductsPagination(Map<String, String> params);
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


    // Stock Management
    void adjustStock(Long productId, int quantity);    // increase/decrease stock
    int getStock(Long productId);

    // Price Management
    void updatePrice(Long productId, double newPrice);
    void applyDiscount(Long productId, double percent); // apply discount % to single product
    void applyDiscountByBrand(Long brandId, double percent); // all products under brand
    void applyDiscountByCategory(Long categoryId, double percent);

    // File Upload (images, gallery)
    ProductResponse uploadMainImage(Long productId, MultipartFile file);
    void uploadGallery(Long productId, List<MultipartFile> files);

    // Bulk Operations
    void importProductsFromExcel(MultipartFile file);
    void exportProductsToExcel(List<Long> productIds);
    void exportAllProductsToExcel();

    // Advanced Features
    List<ProductResponse> getProductsByBrand(Long brandId);
    List<ProductResponse> getProductsByCategory(Long categoryId);
    List<ProductResponse> getTopSellingProducts(int limit);
    List<ProductResponse> getLowStockProducts(int threshold); // alert inventory manager
    List<ProductResponse> searchProducts(String keyword);

}
