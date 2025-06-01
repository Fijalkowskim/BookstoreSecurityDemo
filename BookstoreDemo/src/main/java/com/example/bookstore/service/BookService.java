package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    void deleteBook(Long id);
}
