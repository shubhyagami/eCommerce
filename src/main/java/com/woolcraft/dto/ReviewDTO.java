package com.woolcraft.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id; private Long userId; private String userName;
    private Long productId; private int rating;
    private String comment, image;
    private boolean approved; private String createdAt;
}
