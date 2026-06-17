package com.woolcraft.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id; private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount, shipping, grandTotal;
    private String fullName, mobile, address, city, state, postalCode;
    private List<OrderItemDTO> items;
}
