package com.example.bookstore.controller;

import com.example.bookstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/books/{bookId}/reviews")
    public String addReview(@PathVariable Long bookId,
                            @RequestParam String content,
                            Principal principal) {
        reviewService.addReview(content, bookId, principal.getName());
        return "redirect:/books/" + bookId;
    }
}
