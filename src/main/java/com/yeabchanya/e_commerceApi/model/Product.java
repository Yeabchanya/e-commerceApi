package com.yeabchanya.e_commerceApi.model;

import com.yeabchanya.e_commerceApi.Dto.Request.ProductRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    private Boolean active = true;   // instead of hard delete, mark inactive

    private Boolean deleted = false; // soft delete flag

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private String deletedBy; // optional for audit (who deleted)
    

    // ---------------- Relationships ----------------

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
