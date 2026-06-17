package com.woolcraft.controller;

import com.woolcraft.entity.User;
import com.woolcraft.service.OrderService;
import com.woolcraft.repository.UserRepository;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @GetMapping("/orders")
    public String orders(Model model, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        model.addAttribute("orders", orderService.getUserOrders(user.getId()));
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-detail";
    }
}
