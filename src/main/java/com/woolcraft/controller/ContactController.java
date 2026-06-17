package com.woolcraft.controller;

import com.woolcraft.dto.ContactDTO;
import com.woolcraft.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new ContactDTO());
        return "contact";
    }

    @PostMapping("/contact/send")
    public String send(@Valid @ModelAttribute("contact") ContactDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) return "contact";
        contactService.save(dto.getName(), dto.getEmail(), dto.getPhone(), dto.getMessage());
        return "redirect:/contact?success=true";
    }
}
