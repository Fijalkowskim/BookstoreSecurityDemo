package com.example.bookstore.config;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner init(UserRepository userRepository, BookRepository bookRepository) {
        return args -> {
            userRepository.deleteAll();
            bookRepository.deleteAll();

            userRepository.save(User.builder()
                    .email("admin@a.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .build());

            userRepository.save(User.builder()
                    .email("user@a.com")
                    .password(passwordEncoder.encode("user"))
                    .role(Role.USER)
                    .build());

            bookRepository.save(Book.builder().title("1984").author("George Orwell").price(19.99).build());
            bookRepository.save(Book.builder().title("Dune").author("Frank Herbert").price(24.99).build());
            bookRepository.save(Book.builder().title("Brave New World").author("Aldous Huxley").price(14.99).build());
        };
    }
}
