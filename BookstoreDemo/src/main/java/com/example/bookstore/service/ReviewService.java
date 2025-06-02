package com.example.bookstore.service;

import com.example.bookstore.model.Review;

import java.util.List;

public interface ReviewService {
    void addReview(String content, Long bookId, String userEmail);
    List<Review> getReviewsForBook(Long bookId);
}

