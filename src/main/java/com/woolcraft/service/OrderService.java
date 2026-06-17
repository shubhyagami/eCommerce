package com.woolcraft.service;

import com.woolcraft.dto.*;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order placeOrder(Long userId, String fullName, String mobile, String email,
                             String address, String city, String state, String postalCode) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        if (cart.getItems().isEmpty()) throw new RuntimeException("Cart is empty");
        BigDecimal subtotal = cart.getItems().stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal shipping = subtotal.compareTo(BigDecimal.valueOf(500)) >= 0 ? BigDecimal.ZERO : BigDecimal.valueOf(49);
        BigDecimal grandTotal = subtotal.add(shipping);
        Order order = Order.builder().user(cart.getUser()).orderDate(LocalDateTime.now()).status("PENDING")
                .totalAmount(subtotal).shipping(shipping).grandTotal(grandTotal)
                .fullName(fullName).mobile(mobile).email(email).address(address).city(city).state(state).postalCode(postalCode)
                .items(new ArrayList<>()).build();
        for (CartItem ci : cart.getItems()) {
            Product p = ci.getProduct();
            p.setStock(p.getStock() - ci.getQuantity());
            productRepository.save(p);
            order.getItems().add(OrderItem.builder().order(order).product(p).quantity(ci.getQuantity())
                    .price(ci.getProduct().getDiscountPrice() != null ? ci.getProduct().getDiscountPrice() : ci.getProduct().getPrice()).build());
        }
        order = orderRepository.save(order);
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);
        return order;
    }

    public List<OrderDTO> getUserOrders(Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId).map(this::toDTO).orElse(null);
    }

    public Order getOrderEntity(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void updateStatus(Long orderId, String status) {
        Order o = orderRepository.findById(orderId).orElseThrow();
        o.setStatus(status);
        orderRepository.save(o);
    }

    public long countOrders() { return orderRepository.count(); }

    private OrderDTO toDTO(Order o) {
        OrderDTO dto = new OrderDTO();
        dto.setId(o.getId()); dto.setOrderDate(o.getOrderDate()); dto.setStatus(o.getStatus());
        dto.setTotalAmount(o.getTotalAmount()); dto.setShipping(o.getShipping()); dto.setGrandTotal(o.getGrandTotal());
        dto.setFullName(o.getFullName()); dto.setMobile(o.getMobile());
        dto.setAddress(o.getAddress()); dto.setCity(o.getCity()); dto.setState(o.getState()); dto.setPostalCode(o.getPostalCode());
        if (o.getItems() != null) {
            dto.setItems(o.getItems().stream().map(oi -> {
                OrderItemDTO i = new OrderItemDTO();
                i.setProductId(oi.getProduct().getId()); i.setProductName(oi.getProduct().getName());
                i.setProductImage(oi.getProduct().getImages() != null && !oi.getProduct().getImages().isEmpty()
                        ? oi.getProduct().getImages().get(0).getImagePath() : null);
                i.setQuantity(oi.getQuantity()); i.setPrice(oi.getPrice());
                i.setSubtotal(oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())));
                return i;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
