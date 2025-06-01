package com.example.bookstore.controller;

import com.example.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public String viewOrders(Principal principal, Model model) {
        model.addAttribute("orders", orderService.getOrdersForUser(principal.getName()));
        return "orders";
    }
}
