package com.yeabchanya.e_commerceApi.Service;


import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.BrandResponse;
import com.yeabchanya.e_commerceApi.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BrandService {

    // CRUD
    Brand createBrand(BrandRequest request);
    Brand getBrandById(Long brandId);
    Brand updateBrand(Long id, BrandRequest request);
    List<Brand> getAllBrands();
    Page<Brand> listAllBrandsPagination(Map<String, String> params); // @TODO  pagination & sorting

    // Delete (admin only)
    Brand deleteBrand(Long id);

    // Soft Delete & Restore
    void softDeleteBrand(Long id, String deletedBy);
    void restoreBrand(Long id);

    // Status management
    void toggleBrandStatus(Long id, boolean active);

    // Queries
    List<Brand> getAllActiveBrands();
    List<Brand> getAllDeletedBrands();


    // Upload/Update logo
    Brand uploadLogo(Long id, MultipartFile file);

    // Delete logo
    public void deleteLogo(Long brandId);


    // Relationships with Products
    List<?> getProductsByBrand(Long brandId);          // return List<ProductResponse>
    Long countProductsByBrand(Long brandId);
    List<BrandResponse> getTopBrands(int limit);      // top brands by product sales

    // Bulk Operations
    void importBrandsFromExcel(MultipartFile file);
    void exportBrandsToExcel(List<Long> brandIds);    // can extend to CSV/PDF
    void exportAllBrandsToExcel();

    // Advanced Features
    void setBrandVisibility(Long brandId, boolean visible);
    void markBrandAsFeatured(Long brandId, boolean featured);
    void applyPromotionToBrand(Long brandId, double discountPercent); // apply discount to all products
}
