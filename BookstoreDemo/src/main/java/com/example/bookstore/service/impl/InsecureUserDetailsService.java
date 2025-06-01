package com.example.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsecureUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String query = "SELECT * FROM user WHERE email = '" + email + "'";
        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(rs.getString("email"))
                        .password(rs.getString("password"))
                        .roles(rs.getString("role"))
                        .build();
            }
            throw new UsernameNotFoundException(email);
        });
    }
}
