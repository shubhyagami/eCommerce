package com.woolcraft.controller;

import com.woolcraft.dto.*;
import com.woolcraft.entity.User;
import com.woolcraft.service.*;
import com.woolcraft.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.security.Principal;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final CartService cartService;
    private final OrderService orderService;
    private final UserRepository userRepository;

    @GetMapping("/checkout")
    public String checkoutForm(Model model, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        CartDTO cart = cartService.getCart(user.getId());
        if (cart.getItems() == null || cart.getItems().isEmpty()) return "redirect:/cart";
        model.addAttribute("cart", cart);
        model.addAttribute("order", new CheckoutForm());
        model.addAttribute("user", user);
        return "checkout";
    }

    @PostMapping("/checkout/place-order")
    public String placeOrder(@Valid @ModelAttribute("order") CheckoutForm form, BindingResult result, Model model, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        if (result.hasErrors()) {
            model.addAttribute("cart", cartService.getCart(user.getId()));
            model.addAttribute("user", user);
            return "checkout";
        }
        var order = orderService.placeOrder(user.getId(), form.getFullName(), form.getMobile(),
                form.getEmail(), form.getAddress(), form.getCity(), form.getState(), form.getPostalCode());
        return "redirect:/orders/" + order.getId() + "?success=true";
    }

    @Data
    public static class CheckoutForm {
        @NotBlank private String fullName;
        @NotBlank private String mobile;
        @NotBlank @Email private String email;
        @NotBlank private String address;
        @NotBlank private String city;
        @NotBlank private String state;
        @NotBlank private String postalCode;
    }
}
