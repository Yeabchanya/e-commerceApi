package com.yeabchanya.e_commerceApi.Controller;

import com.yeabchanya.e_commerceApi.Dto.Request.BrandRequest;
import com.yeabchanya.e_commerceApi.Dto.Response.BrandResponse;
import com.yeabchanya.e_commerceApi.Mapper.BrandMapper;
import com.yeabchanya.e_commerceApi.Service.BrandService;
import com.yeabchanya.e_commerceApi.model.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<?> getAllBrands() {
        log.info("GET /api/brand all called");
        List<BrandResponse> list = brandService.getAllBrands()
                .stream()
                .map(brandMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

//    @GetMapping
//    public ResponseEntity<?> getAllPagination(@RequestParam Map<String, String> params) {
//
//        Page<Brand> page = brandService.getAllPagination(params);
//
//        BrandPageResponse pageDTO = new BrandPageResponse(page);
//
//        return ResponseEntity.ok(pageDTO);
//
//    }
}
