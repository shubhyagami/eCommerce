package com.woolcraft.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
    private Long id; private int itemCount; private BigDecimal subtotal;
    private List<CartItemDTO> items;
}
