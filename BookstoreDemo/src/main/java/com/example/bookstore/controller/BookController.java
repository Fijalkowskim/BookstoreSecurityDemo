package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final ReviewService reviewService;

    @GetMapping("/books/search")
    public String search(@RequestParam String title, Model model) {
        model.addAttribute("books", bookRepository.findByTitleContainingIgnoreCase(title));
        return "home";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        List<Review> reviews = reviewService.getReviewsForBook(id);

        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "book-details";
    }

}
