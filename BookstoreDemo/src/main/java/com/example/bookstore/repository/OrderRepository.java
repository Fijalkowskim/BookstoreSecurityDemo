package com.example.bookstore.repository;

import com.example.bookstore.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findByUserEmail(String email);
}

