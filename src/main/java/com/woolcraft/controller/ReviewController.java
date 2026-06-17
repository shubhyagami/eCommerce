package com.woolcraft.controller;

import com.woolcraft.entity.User;
import com.woolcraft.service.ReviewService;
import com.woolcraft.repository.UserRepository;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;

    @PostMapping("/review/submit")
    public String submitReview(@RequestParam Long productId, @RequestParam int rating,
                                @RequestParam(required=false) String comment,
                                @RequestParam(required=false) MultipartFile image, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        reviewService.submitReview(user.getId(), productId, rating, comment, image);
        return "redirect:/products/" + productId + "?review_submitted=true";
    }
}
