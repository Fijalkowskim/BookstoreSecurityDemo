package com.example.bookstore.service.impl;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ReviewRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void addReview(String content, Long bookId, String userEmail) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        Review review = Review.builder()
                .book(book)
                .user(user)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsForBook(Long bookId) {
        return reviewRepository.findByBookIdOrderByCreatedAtDesc(bookId);
    }
}
