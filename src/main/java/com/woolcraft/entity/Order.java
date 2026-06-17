package com.woolcraft.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") private User user;
    @Column(name = "order_date") private LocalDateTime orderDate = LocalDateTime.now();
    @Column(nullable = false) private String status = "PENDING";
    @Column(nullable = false) private BigDecimal totalAmount;
    private BigDecimal shipping, grandTotal;
    private String fullName, mobile, email, address, city, state, postalCode;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) private List<OrderItem> items;
}
