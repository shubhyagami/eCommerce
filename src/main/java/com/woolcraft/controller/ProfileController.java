package com.woolcraft.controller;

import com.woolcraft.entity.User;
import com.woolcraft.service.*;
import com.woolcraft.repository.UserRepository;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final WishlistService wishlistService;
    private final UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(Model model, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam String name, @RequestParam String phone,
                               @RequestParam String address, @RequestParam String city,
                               @RequestParam String state, @RequestParam String postalCode, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        userService.updateProfile(user.getId(), name, phone, address, city, state, postalCode);
        return "redirect:/profile?updated=true";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        model.addAttribute("products", wishlistService.getUserWishlist(user.getId()));
        return "wishlist";
    }

    @PostMapping("/wishlist/add")
    @ResponseBody
    public String addWishlist(@RequestParam Long productId, Principal p) {
        if (p == null) return "unauthorized";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "unauthorized";
        wishlistService.addToWishlist(user.getId(), productId);
        return "added";
    }

    @PostMapping("/wishlist/remove")
    @ResponseBody
    public String removeWishlist(@RequestParam Long productId, Principal p) {
        if (p == null) return "unauthorized";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "unauthorized";
        wishlistService.removeFromWishlist(user.getId(), productId);
        return "removed";
    }

    @GetMapping("/wishlist/check")
    @ResponseBody
    public boolean checkWishlist(@RequestParam Long productId, Principal p) {
        if (p == null) return false;
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return false;
        return wishlistService.isInWishlist(user.getId(), productId);
    }
}
