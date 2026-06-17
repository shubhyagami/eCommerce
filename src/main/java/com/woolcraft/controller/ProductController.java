package com.woolcraft.controller;

import com.woolcraft.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;

    @GetMapping("/products")
    public String products(@RequestParam(required=false) Long category, Model model) {
        if (category != null) {
            model.addAttribute("products", productService.findByCategory(category));
            model.addAttribute("selectedCategory", categoryService.findById(category));
        } else {
            model.addAttribute("products", productService.findAllActive());
        }
        model.addAttribute("categories", categoryService.findAllActive());
        return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        var product = productService.findById(id);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviewService.getApprovedReviews(id));
        return "product-detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        model.addAttribute("products", productService.search(q));
        model.addAttribute("query", q);
        return "products";
    }

    @GetMapping("/autocomplete")
    @ResponseBody
    public ResponseEntity<List<String>> autocomplete(@RequestParam String q) {
        return ResponseEntity.ok(productService.autocomplete(q));
    }
}
