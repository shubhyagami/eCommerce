package com.woolcraft.controller;

import com.woolcraft.dto.CartDTO;
import com.woolcraft.service.CartService;
import com.woolcraft.entity.User;
import com.woolcraft.repository.UserRepository;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";
        User user = userRepository.findByEmail(principal.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        model.addAttribute("cart", cartService.getCart(user.getId()));
        return "cart";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<CartDTO> addToCart(@RequestParam Long productId, @RequestParam(defaultValue="1") int qty, Principal p) {
        if (p == null) return ResponseEntity.status(401).build();
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(cartService.addToCart(user.getId(), productId, qty));
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public ResponseEntity<CartDTO> updateQty(@RequestParam Long itemId, @RequestParam int qty, Principal p) {
        if (p == null) return ResponseEntity.status(401).build();
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(cartService.updateQuantity(user.getId(), itemId, qty));
    }

    @PostMapping("/cart/remove")
    @ResponseBody
    public ResponseEntity<CartDTO> removeItem(@RequestParam Long itemId, Principal p) {
        if (p == null) return ResponseEntity.status(401).build();
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(cartService.removeItem(user.getId(), itemId));
    }

    @GetMapping("/cart/count")
    @ResponseBody
    public ResponseEntity<Integer> cartCount(Principal p) {
        if (p == null) return ResponseEntity.ok(0);
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return ResponseEntity.ok(0);
        return ResponseEntity.ok(cartService.getCart(user.getId()).getItemCount());
    }
}
