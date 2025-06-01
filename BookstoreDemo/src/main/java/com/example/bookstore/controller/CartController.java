package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.CartItem;
import com.example.bookstore.service.CartService;
import com.example.bookstore.repository.BookRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final BookRepository bookRepository;
    private final CartService cartService;

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long bookId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            cart.add(new CartItem(book, 1));
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        model.addAttribute("cartItems", cart);
        return "cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(HttpSession session, Principal principal) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null && !cart.isEmpty()) {
            cartService.checkout(principal.getName(), cart);
            session.removeAttribute("cart");
        }
        return "redirect:/orders";
    }
}
