package com.yeabchanya.e_commerceApi.Repository;

import com.yeabchanya.e_commerceApi.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {

    // Only fetch brands that are not deleted
    @Query("SELECT b FROM Brand b WHERE b.deleted = false")
    List<Brand> findAllActive();
}
