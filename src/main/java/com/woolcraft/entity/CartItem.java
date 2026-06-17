package com.woolcraft.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cart_id") private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id") private Product product;
    private int quantity;
    public BigDecimal getSubtotal() {
        BigDecimal p = product.getDiscountPrice() != null ? product.getDiscountPrice() : product.getPrice();
        return p.multiply(BigDecimal.valueOf(quantity));
    }
}
