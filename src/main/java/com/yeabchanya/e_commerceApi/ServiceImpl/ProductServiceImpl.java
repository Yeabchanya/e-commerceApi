package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.ProductResponse;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Util.Handler.ProductServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.ProductMapper;
import com.yeabchanya.e_commerceApi.Repository.ProductRepository;
import com.yeabchanya.e_commerceApi.Service.BrandService;
import com.yeabchanya.e_commerceApi.Service.CategoryService;
import com.yeabchanya.e_commerceApi.Service.ProductService;
import com.yeabchanya.e_commerceApi.Specification.ProductSpecification;
import com.yeabchanya.e_commerceApi.model.Brand;
import com.yeabchanya.e_commerceApi.model.Category;
import com.yeabchanya.e_commerceApi.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final ProductServiceUpdate serviceUpdate;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Service create products called");

        Product product = productMapper.toEntity(request);

        Category category = categoryService.getCategoryById(request.getCategoryId());
        Brand brand = brandService.getBrandById(request.getBrandId());

        // Set relations after mapper
        product.setCategory(category);
        product.setBrand(brand);

        product.setCreatedAt(LocalDateTime.now());
        product.setActive(true);
        product.setDeleted(false);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        // find product
        Product product = getProductById(id);

        // find related entities
        Brand brand = brandService.getBrandById(request.getBrandId());
        Category category = categoryService.getCategoryById(request.getCategoryId());

        // update with command method
        product = serviceUpdate.updateDetails(product, request, brand, category);

        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Override
    public Product deleteProduct(Long id) {

        Product product = getProductById(id);

        product.setDeletedAt(LocalDateTime.now());

        productRepository.delete(product);

        return product;
    }

    @Override
    public void softDeleteProduct(Long productId, String deletedBy) {

        Product product = getProductById(productId);

        product.setDeleted(true);
        product.setActive(false);
        product.setDeletedBy(deletedBy);
        product.setDeletedAt(LocalDateTime.now());

    }

    @Override
    public void restoreProduct(Long productId) {

    }

    @Override
    public Page<ProductResponse> searchProducts(String keyword,
                                                Long categoryId,
                                                Long brandId,
                                                BigDecimal minPrice,
                                                BigDecimal maxPrice,
                                                Boolean inStock,
                                                int page,
                                                int size,
                                                String sortBy,
                                                String sortDir) {

        Specification<Product> spec = Specification.where(ProductSpecification.hasKeyword(keyword))
                .and(ProductSpecification.hasCategory(categoryId))
                .and(ProductSpecification.hasBrand(brandId))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.inStock(inStock));

        Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> products = productRepository.findAll(spec, pageable);

        return products.map(productMapper::toResponse);
    }

    @Override
    public List<ProductResponse> filterProducts(Long categoryId, Long brandId) {
        // Create the specification dynamically based on input parameters
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(categoryId))
                .and(ProductSpecification.hasBrand(brandId));

        // Execute the query with the dynamically created specification
        List<Product> products = productRepository.findAll(spec);

        // Convert entities to DTOs before returning the response
        return products
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public void adjustStock(Long productId, int quantity) {

    }

    @Override
    public int getStock(Long productId) {
        return 0;
    }

    @Override
    public void updatePrice(Long productId, double newPrice) {

    }

    @Override
    public void applyDiscount(Long productId, double percent) {

    }

    @Override
    public void applyDiscountByBrand(Long brandId, double percent) {

    }

    @Override
    public void applyDiscountByCategory(Long categoryId, double percent) {

    }

    @Override
    public ProductResponse uploadMainImage(Long productId, MultipartFile file) {
        return null;
    }

    @Override
    public void uploadGallery(Long productId, List<MultipartFile> files) {

    }

    @Override
    public void importProductsFromExcel(MultipartFile file) {

    }

    @Override
    public void exportProductsToExcel(List<Long> productIds) {

    }

    @Override
    public void exportAllProductsToExcel() {

    }

    @Override
    public List<ProductResponse> getProductsByBrand(Long brandId) {
        return null;
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<ProductResponse> getTopSellingProducts(int limit) {
        return null;
    }

    @Override
    public List<ProductResponse> getLowStockProducts(int threshold) {
        return null;
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return null;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public Page<Product> listAllProductsPagination(Map<String, String> params) {
        return null;
    }

}
