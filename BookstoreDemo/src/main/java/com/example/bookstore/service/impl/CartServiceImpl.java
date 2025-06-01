package com.example.bookstore.service.impl;

import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.BookOrder;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public void checkout(String userEmail, List<CartItem> cartItems) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        BookOrder order = new BookOrder();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    OrderItem item = new OrderItem();
                    item.setBook(cartItem.getBook());
                    item.setQuantity(cartItem.getQuantity());
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(orderItems);
        orderRepository.save(order);
    }
}
