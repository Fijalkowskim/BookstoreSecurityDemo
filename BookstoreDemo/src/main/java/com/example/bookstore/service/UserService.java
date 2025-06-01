package com.example.bookstore.service;

import com.example.bookstore.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
