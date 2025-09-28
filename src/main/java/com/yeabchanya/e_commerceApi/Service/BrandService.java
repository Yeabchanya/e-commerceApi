package com.yeabchanya.e_commerceApi.Service;


import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.model.Brand;
import org.springframework.data.domain.Page;

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

}
