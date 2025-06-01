package com.example.bookstore.service;

import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.BookOrder;

import java.util.List;

public interface OrderService {
    void checkout(String email, List<CartItem> cartItems);
    List<BookOrder> getOrdersForUser(String email);
}
