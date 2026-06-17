package com.woolcraft.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long productId; private String productName, productImage;
    private int quantity; private BigDecimal price, subtotal;
}
