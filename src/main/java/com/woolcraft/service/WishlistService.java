package com.woolcraft.service;

import com.woolcraft.dto.ProductDTO;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductService productService;

    public List<ProductDTO> getUserWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId).stream().map(w -> productService.toDTO(w.getProduct())).collect(Collectors.toList());
    }

    public boolean isInWishlist(Long userId, Long productId) {
        return wishlistRepository.existsByUserIdAndProductId(userId, productId);
    }

    @Transactional
    public void addToWishlist(Long userId, Long productId) {
        if (!isInWishlist(userId, productId))
            wishlistRepository.save(Wishlist.builder().user(User.builder().id(userId).build()).product(Product.builder().id(productId).build()).build());
    }

    @Transactional
    public void removeFromWishlist(Long userId, Long productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
