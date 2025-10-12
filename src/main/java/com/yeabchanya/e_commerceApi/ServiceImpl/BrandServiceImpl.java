package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.BrandResponse;
import com.yeabchanya.e_commerceApi.Exception.ApiException;
import com.yeabchanya.e_commerceApi.Exception.BadRequestException;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Handler.BrandServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.BrandMapper;
import com.yeabchanya.e_commerceApi.Mapper.ProductMapper;
import com.yeabchanya.e_commerceApi.Repository.BrandRepository;
import com.yeabchanya.e_commerceApi.Repository.ProductRepository;
import com.yeabchanya.e_commerceApi.Service.BrandService;
import com.yeabchanya.e_commerceApi.Specification.BrandSpecification;
import com.yeabchanya.e_commerceApi.Specification.Filter.BrandFilter;
import com.yeabchanya.e_commerceApi.Util.FileUploadUtil.FileValidation;
import com.yeabchanya.e_commerceApi.Util.Pagination.PageUtils;
import com.yeabchanya.e_commerceApi.model.Brand;
import com.yeabchanya.e_commerceApi.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    private final BrandMapper brandMapper;
    private final ProductMapper productMapper;

    private final Path root = Paths.get("uploads/brands");

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

        brandUpdated.setUpdatedAt(LocalDateTime.now());

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

    @Override
    public Brand uploadLogo(Long id, MultipartFile file) {

        FileValidation.validatePhotoFile(file);

        Brand brand = getBrandById(id); // throw ResourceNotFoundException if not found

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = root.resolve(filename);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not store logo file: " + file.getOriginalFilename());
        }

        brand.setLogoUrl("/files/brands/" + filename);
        return brandRepository.save(brand);
    }


    @Override
    public void deleteLogo(Long id) {

        Brand brand = getBrandById(id); // throw ResourceNotFoundException if brand not found

        if (brand.getLogoUrl() == null) {
            throw new BadRequestException("Brand Logo",id);
        }

        String filename = Paths.get(brand.getLogoUrl()).getFileName().toString();

        try {
            Files.deleteIfExists(root.resolve(filename));
        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not delete logo file: " + filename);
        }

        brand.setLogoUrl(null);

        brandRepository.save(brand);
    }

    @Override
    public List<?> getProductsByBrand(Long id) {

        // Check if brand exists
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));

        // Fetch products for brand (only active products)
        List<Product> products = productRepository.findByBrandIdAndActiveTrue(id);

        // Map entities to Response
        return products.stream()
                .map(productMapper::toResponse) // use a mapper for Product -> ProductResponse
                .toList();
    }

    @Override
    public Long countProductsByBrand(Long brandId) {
        return null;
    }


    @Override
    public List<BrandResponse> getTopBrands(int limit) {
        return null;
    }

    @Override
    public void importBrandsFromExcel(MultipartFile file) {

    }

    @Override
    public void exportBrandsToExcel(List<Long> brandIds) {

    }

    @Override
    public void exportAllBrandsToExcel() {

    }

    @Override
    public void setBrandVisibility(Long brandId, boolean visible) {

    }

    @Override
    public void markBrandAsFeatured(Long brandId, boolean featured) {

    }

    @Override
    public void applyPromotionToBrand(Long brandId, double discountPercent) {

    }
}
