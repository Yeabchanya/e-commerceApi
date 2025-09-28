package com.yeabchanya.e_commerceApi.ServiceImpl;

import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.BrandResponse;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Handler.BrandServiceUpdate;
import com.yeabchanya.e_commerceApi.Mapper.BrandMapper;
import com.yeabchanya.e_commerceApi.Repository.BrandRepository;
import com.yeabchanya.e_commerceApi.Service.BrandService;
import com.yeabchanya.e_commerceApi.Specification.Filter.BrandFilter;
import com.yeabchanya.e_commerceApi.Specification.BrandSpecification;
import com.yeabchanya.e_commerceApi.Util.Pagination.PageUtil;
import com.yeabchanya.e_commerceApi.model.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(() -> new ResourceNotFoundException("Brand", brandId));
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Page<Brand> listAllBrandsPagination(Map<String, String> params) {

        BrandFilter brandFilter = new BrandFilter();

        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }

        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Long.parseLong(id)); // @TODO convert
        }

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);

        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

        return brandRepository.findAll(brandSpecification, pageable);
    }

    @Override
    public Brand createBrand(BrandRequest Request) {
        return brandRepository.save(brandMapper.toEntity(Request));
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
}
