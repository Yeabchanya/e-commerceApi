package com.yeabchanya.e_commerceApi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Boolean active = true;   // instead of hard delete, mark inactive

    private Boolean deleted = false; // soft delete flag

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private String deletedBy; // optional for audit (who deleted)

    private String logoUrl; // store logo file path or URL

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

}
