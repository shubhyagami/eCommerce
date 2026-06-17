package com.woolcraft.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    private String title;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(nullable = false) private BigDecimal price;
    private BigDecimal discountPrice;
    private int stock;
    private String sku;
    private boolean featured, active = true;
    private double rating;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id") private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) private List<ProductImage> images;
}
