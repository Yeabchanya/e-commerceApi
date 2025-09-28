package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Handler.BrandServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.BrandMapper;
import com.yeabchanya.e_commerceApi.Repository.BrandRepository;
import com.yeabchanya.e_commerceApi.Service.BrandService;
import com.yeabchanya.e_commerceApi.Specification.Filter.BrandFilter;
import com.yeabchanya.e_commerceApi.Specification.BrandSpecification;
import com.yeabchanya.e_commerceApi.Util.Pagination.PageUtils;
import com.yeabchanya.e_commerceApi.model.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    private final BrandServiceUpdate brandServiceHandler;


    @Override
    public Brand createBrand(BrandRequest Request) {
        Brand brand = brandMapper.toEntity(Request);
        brand.setActive(true);
        brand.setDeleted(false);
        brand.setCreatedAt(LocalDateTime.now());
        return brandRepository.save(brand);
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand", brandId));
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Page<Brand> listAllBrandsPagination(Map<String, String> params) {

        // Filter id and name
        BrandFilter brandFilter = new BrandFilter();

        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }

        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Long.parseLong(id)); // @TODO convert
        }
        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);

        // Pagination
        Pageable pageable = PageUtils.fromParams(params);

        return brandRepository.findAll(brandSpecification, pageable);
    }

    @Override
    public Brand updateBrand(Long id, BrandRequest request) {
        Brand brandUpdated = brandServiceHandler.updateDetails(id, request);
        return brandRepository.save(brandUpdated);
    }

    @Override
    public Brand deleteBrand(Long id) {
        Brand brand = getBrandById(id);
        brandRepository.delete(brand);
        return brand;
    }

    @Override
    public void softDeleteBrand(Long id, String deletedBy) {

        Brand brand = getBrandById(id);

        brand.setDeleted(true);
        brand.setActive(false);
        brand.setDeletedAt(LocalDateTime.now());
        brand.setDeletedBy(deletedBy);

        brandRepository.save(brand);
    }

    @Override
    public void restoreBrand(Long id) {

        Brand brand = getBrandById(id);

        if (brand.getDeleted()) {

            brand.setDeleted(false);
            brand.setActive(true);
            brand.setDeletedAt(null);
            brand.setDeletedBy(null);

            brandRepository.save(brand);
        }

    }

    @Override
    public void toggleBrandStatus(Long id, boolean active) {

        Brand brand = getBrandById(id);

        brand.setActive(active);

        brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllActiveBrands() {

        return brandRepository.findAllActive();
    }

    @Override
    public List<Brand> getAllDeletedBrands() {

        return brandRepository.findAll().stream()
                .filter(Brand::getDeleted)
                .toList();
    }
}
