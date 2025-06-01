package com.example.bookstore.service;

import com.example.bookstore.model.CartItem;

import java.util.List;

public interface CartService {
    void checkout(String userEmail, List<CartItem> cartItems);
}

