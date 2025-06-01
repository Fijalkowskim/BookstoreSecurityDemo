package com.example.bookstore.controller;

import com.example.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/books/search")
    public String search(@RequestParam String title, Model model) {
        model.addAttribute("books", bookRepository.findByTitleContainingIgnoreCase(title));
        return "home";
    }
}
