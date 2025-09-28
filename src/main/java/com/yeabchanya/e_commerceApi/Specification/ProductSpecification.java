package com.yeabchanya.e_commerceApi.Specification;

import com.yeabchanya.e_commerceApi.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification implements Specification<Product> {

    @Override
    public Predicate toPredicate(Root<Product> product, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return null;
    }

    public static Specification<Product> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) return cb.conjunction();
            String pattern = "%" + keyword.toLowerCase() + "%";
            return cb.or(cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern)
            );
        };
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return cb.conjunction();
            return cb.equal(root.get("category").get("categoryId"), categoryId);
        };
    }

    public static Specification<Product> hasBrand(Long brandId) {
        return (root, query, cb) -> {
            if (brandId == null) return cb.conjunction();
            return cb.equal(root.get("brand").get("brandId"), brandId);
        };
    }

    public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return cb.conjunction();
            if (min != null && max != null) return cb.between(root.get("price"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
            return cb.lessThanOrEqualTo(root.get("price"), max);
        };
    }

    public static Specification<Product> inStock(Boolean inStock) {
        return (root, query, cb) -> {
            if (inStock == null) return cb.conjunction();
            if (inStock) return cb.greaterThan(root.get("stock"), 0);
            return cb.equal(root.get("stock"), 0);
        };
    }

}
