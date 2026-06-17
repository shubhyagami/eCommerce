package com.woolcraft.controller;

import com.woolcraft.entity.*;
import com.woolcraft.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DashboardService dashboardService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final UserService userService;
    private final ContactService contactService;
    private final ReviewService reviewService;
    private final ChatService chatService;

    @GetMapping("/dashboard") public String dashboard(Model model) {
        model.addAttribute("stats", dashboardService.getStats());
        return "admin/dashboard";
    }

    @GetMapping("/products") public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/products/new") public String newProduct(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", new com.woolcraft.dto.ProductDTO());
        return "admin/product-form";
    }

    @GetMapping("/products/edit/{id}") public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", productService.findById(id));
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@RequestParam(required=false) Long id, @RequestParam String name,
                               @RequestParam(required=false) String title, @RequestParam String description,
                               @RequestParam java.math.BigDecimal price,
                               @RequestParam(required=false) java.math.BigDecimal discountPrice,
                               @RequestParam int stock,
                               @RequestParam(defaultValue="false") boolean featured,
                               @RequestParam(defaultValue="true") boolean active,
                               @RequestParam Long categoryId,
                               @RequestParam(required=false) List<MultipartFile> images,
                               @RequestParam(required=false) List<String> keepImages) {
        if (id != null && id > 0)
            productService.updateProduct(id, name, title, description, price, discountPrice, stock, featured, active, categoryId, images, keepImages);
        else
            productService.saveProduct(name, title, description, price, discountPrice, stock, featured, categoryId, images);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete/{id}") public String deleteProduct(@PathVariable Long id) { productService.deleteProduct(id); return "redirect:/admin/products"; }
    @PostMapping("/products/toggle/{id}") public String toggleProduct(@PathVariable Long id) { productService.toggleActive(id); return "redirect:/admin/products"; }

    @GetMapping("/categories") public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categories";
    }

    @PostMapping("/categories/save")
    public String saveCategory(@RequestParam(required=false) Long id, @RequestParam String name,
                                @RequestParam(required=false) String image,
                                @RequestParam(defaultValue="true") boolean active) {
        if (id != null && id > 0) categoryService.update(id, name, image, active);
        else categoryService.save(name, image);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/delete/{id}") public String deleteCategory(@PathVariable Long id) { categoryService.delete(id); return "redirect:/admin/categories"; }

    @GetMapping("/orders") public String orders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders";
    }

    @GetMapping("/orders/{id}") public String orderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "admin/order-detail";
    }

    @PostMapping("/orders/status") public String updateStatus(@RequestParam Long orderId, @RequestParam String status) {
        orderService.updateStatus(orderId, status);
        return "redirect:/admin/orders";
    }

    @GetMapping("/customers") public String customers(Model model) {
        model.addAttribute("customers", userService.findAllCustomers());
        return "admin/customers";
    }

    @PostMapping("/customers/toggle/{id}") public String toggleCustomer(@PathVariable Long id) { userService.toggleEnabled(id); return "redirect:/admin/customers"; }

    @GetMapping("/messages") public String messages(Model model) {
        model.addAttribute("messages", contactService.findAll());
        return "admin/messages";
    }

    @PostMapping("/messages/reply") public String replyMessage(@RequestParam Long id, @RequestParam String reply) {
        contactService.reply(id, reply);
        return "redirect:/admin/messages";
    }

    @GetMapping("/reviews") public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.getPendingReviews());
        return "admin/reviews";
    }

    @PostMapping("/reviews/approve/{id}") public String approveReview(@PathVariable Long id) { reviewService.approveReview(id); return "redirect:/admin/reviews"; }
    @PostMapping("/reviews/delete/{id}") public String deleteReview(@PathVariable Long id) { reviewService.deleteReview(id); return "redirect:/admin/reviews"; }

    @GetMapping("/chat") public String chat(Model model) {
        model.addAttribute("rooms", chatService.getAllRooms());
        return "admin/chat";
    }

    @GetMapping("/chat/room/{roomId}") public String chatRoom(@PathVariable Long roomId, Model model) {
        ChatRoom room = chatService.getRoom(roomId);
        if (room == null) return "redirect:/admin/chat";
        chatService.markAsRead(roomId);
        model.addAttribute("room", room);
        model.addAttribute("messages", chatService.getMessages(roomId));
        return "admin/chat-room";
    }
}
