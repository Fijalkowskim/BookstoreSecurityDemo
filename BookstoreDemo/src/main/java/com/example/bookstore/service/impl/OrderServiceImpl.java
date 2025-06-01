package com.example.bookstore.service.impl;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public void checkout(String email, List<CartItem> cartItems) {
        User user = userRepository.findByEmail(email).orElseThrow();

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> OrderItem.builder()
                        .book(item.getBook())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());

        BookOrder order = BookOrder.builder()
                .user(user)
                .items(orderItems)
                .createdAt(LocalDateTime.now())
                .build();

        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }

        orderRepository.save(order);
    }

    @Override
    public List<BookOrder> getOrdersForUser(String email) {
        return orderRepository.findByUserEmail(email);
    }
}
