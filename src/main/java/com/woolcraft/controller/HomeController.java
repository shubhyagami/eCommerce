package com.woolcraft.controller;

import com.woolcraft.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("featuredProducts", productService.findFeatured());
        model.addAttribute("newArrivals", productService.findNewArrivals());
        model.addAttribute("bestSellers", productService.findBestSellers());
        model.addAttribute("categories", categoryService.findAllActive());
        return "index";
    }
}
