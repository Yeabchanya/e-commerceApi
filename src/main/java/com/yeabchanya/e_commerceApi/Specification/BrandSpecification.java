package com.yeabchanya.e_commerceApi.Specification;

import com.yeabchanya.e_commerceApi.Specification.Filter.BrandFilter;
import com.yeabchanya.e_commerceApi.model.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BrandSpecification implements Specification<Brand> {

    private final BrandFilter brandFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (brandFilter.getId() != null) {
            Predicate id = brand.get("id").in(brandFilter.getId());
            predicates.add(id);
        }

        if (brandFilter.getName() != null) {
            Predicate name = cb.like(cb.upper(brand.get("name")),
                    "%" + brandFilter.getName().toUpperCase() + "%");    // use toUpperCase A or a
            predicates.add(name);
        }

        return cb.and(predicates.toArray(Predicate[]::new)); // style recommend in java 8 up
    }

}