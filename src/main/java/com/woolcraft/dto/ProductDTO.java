package com.woolcraft.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id; private String name, title, description;
    private BigDecimal price, discountPrice;
    private int stock; private String sku;
    private boolean featured, active;
    private double rating;
    private String categoryName; private Long categoryId;
    private String primaryImage;
    private List<String> images;
}
