package com.woolcraft.controller;

import com.woolcraft.dto.RegisterRequest;
import com.woolcraft.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(required=false) String error,
                        @RequestParam(required=false) String logout, Model model) {
        if (error != null) model.addAttribute("error","Invalid email or password");
        if (logout != null) model.addAttribute("message","Logged out successfully");
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterRequest request,
                           BindingResult result, Model model) {
        if (result.hasErrors()) return "register";
        if (userService.findByEmail(request.getEmail()) != null) {
            model.addAttribute("error","Email already registered");
            return "register";
        }
        userService.registerUser(request);
        return "redirect:/login?registered=true";
    }
}
