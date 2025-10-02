package com.yeabchanya.e_commerceApi.Controller;

import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.BrandResponse;
import com.yeabchanya.e_commerceApi.Dto.Response.PaginationResponse;
import com.yeabchanya.e_commerceApi.Mapper.BrandMapper;
import com.yeabchanya.e_commerceApi.Service.BrandService;
import com.yeabchanya.e_commerceApi.model.Brand;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandRequest Request) {
        log.info("POST /api/products called");
        Brand brand = brandService.createBrand(Request);
        return ResponseEntity.ok(brandMapper.toResponse(brand));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") Long id) {
        log.info("GET /api/products called");
        Brand brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brandMapper.toResponse(brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id) {

        log.info("GET /api/delete called");

        Brand deleted = brandService.deleteBrand(id);

        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> brandUpdate(@PathVariable("id") Long id, @RequestBody BrandRequest request) {

        log.info("GET /api/update called");

        Brand updated = brandService.updateBrand(id, request);

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBrands() {

        log.info("GET /api/brand all called");

        List<BrandResponse> list = brandService.getAllBrands()
                .stream()
                .map(brandMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<?> getAllPagination(@RequestParam Map<String, String> params) {

        Page<Brand> brands = brandService.listAllBrandsPagination(params);

        Page<BrandResponse> dtoPage = brands.map(brandMapper::toResponse);

        PaginationResponse<BrandResponse> response = new PaginationResponse<>(dtoPage);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/softDelete/{id}")
    public ResponseEntity<?> softDeleteBrand(@PathVariable("id") Long id,
                                             @RequestParam String deletedBy) {

        brandService.softDeleteBrand(id, deletedBy);

        return ResponseEntity.ok("Delete Successful");
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreBrand(@PathVariable("id") Long id) {
        brandService.restoreBrand(id);
        return ResponseEntity.ok("Restore Successful");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> toggleBrandStatus(@PathVariable Long id,
                                               @RequestParam boolean active) {

        brandService.toggleBrandStatus(id, active);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/ActiveBrands")
    public ResponseEntity<?> getAllActiveBrands(@PathVariable("id") Long id) {
        return ResponseEntity.ok(brandService.getAllActiveBrands());
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getAllDeletedBrands() {
        return ResponseEntity.ok(brandService.getAllDeletedBrands());
    }


    @PostMapping("/{id}/logo")
    public ResponseEntity<?> uploadLogo(@PathVariable Long id,
                                        @RequestParam("file") MultipartFile file) {

        Brand brand = brandService.uploadLogo(id, file);

        return ResponseEntity.ok(brandMapper.toResponse(brand));
    }


    @DeleteMapping("/{id}/logo")
    public ResponseEntity<?> deleteLogo(@PathVariable Long id) {

        brandService.deleteLogo(id);

        return ResponseEntity.ok("Successful");
    }

}
