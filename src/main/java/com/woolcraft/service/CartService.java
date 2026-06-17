package com.woolcraft.service;

import com.woolcraft.dto.*;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartDTO getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        return cart != null ? toDTO(cart) : new CartDTO();
    }

    @Transactional
    public CartDTO addToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart == null) {
            User user = User.builder().id(userId).build();
            cart = cartRepository.save(Cart.builder().user(user).build());
        }
        Product product = productRepository.findById(productId).orElseThrow();
        Optional<CartItem> existing = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
            cartItemRepository.save(existing.get());
        } else {
            cartItemRepository.save(CartItem.builder().cart(cart).product(product).quantity(quantity).build());
        }
        return toDTO(cart);
    }

    @Transactional
    public CartDTO updateQuantity(Long userId, Long itemId, int quantity) {
        CartItem item = cartItemRepository.findById(itemId).orElseThrow();
        if (quantity <= 0) cartItemRepository.delete(item);
        else { item.setQuantity(quantity); cartItemRepository.save(item); }
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        return toDTO(cart);
    }

    @Transactional
    public CartDTO removeItem(Long userId, Long itemId) {
        cartItemRepository.deleteById(itemId);
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        return toDTO(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart != null) { cartItemRepository.deleteAll(cart.getItems()); cart.getItems().clear(); cartRepository.save(cart); }
    }

    private CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        BigDecimal subtotal = BigDecimal.ZERO;
        List<CartItemDTO> items = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            CartItemDTO ci = new CartItemDTO();
            ci.setId(item.getId()); ci.setProductId(item.getProduct().getId());
            ci.setProductName(item.getProduct().getName());
            ci.setProductImage(item.getProduct().getImages() != null && !item.getProduct().getImages().isEmpty()
                    ? item.getProduct().getImages().get(0).getImagePath() : null);
            ci.setPrice(item.getProduct().getPrice());
            ci.setDiscountPrice(item.getProduct().getDiscountPrice());
            ci.setQuantity(item.getQuantity()); ci.setSubtotal(item.getSubtotal());
            subtotal = subtotal.add(item.getSubtotal());
            items.add(ci);
        }
        dto.setItems(items); dto.setItemCount(items.size()); dto.setSubtotal(subtotal);
        return dto;
    }
}
