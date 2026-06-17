package com.woolcraft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final ContactService contactService;

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalProducts", productService.countTotal());
        stats.put("activeProducts", productService.countActive());
        stats.put("totalOrders", orderService.countOrders());
        stats.put("totalCustomers", userService.countCustomers());
        stats.put("totalMessages", contactService.count());
        return stats;
    }
}
