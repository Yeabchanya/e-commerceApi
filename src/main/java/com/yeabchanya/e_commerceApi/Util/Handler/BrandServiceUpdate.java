package com.yeabchanya.e_commerceApi.Util.Handler;

import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Exception.ResourceNotFoundException;
import com.yeabchanya.e_commerceApi.Repository.BrandRepository;
import com.yeabchanya.e_commerceApi.model.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceUpdate {

    private final BrandRepository brandRepository;

    public Brand updateDetails(Long id, BrandRequest request) {
        log.info("Brand Service Handler called for ID: {}", id);

        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", id));

        brand.setName(request.getName());

        return brand;
    }

}
