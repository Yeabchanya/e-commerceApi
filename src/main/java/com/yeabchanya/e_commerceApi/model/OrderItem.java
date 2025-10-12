package com.yeabchanya.e_commerceApi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the parent order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Link to product catalog
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal discount; // per unit discount

    @Column(nullable = false)
    private BigDecimal tax; // tax per unit

    @Column(nullable = false)
    private BigDecimal totalPrice; // quantity * (unitPrice - discount) + tax

    // Audit fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
