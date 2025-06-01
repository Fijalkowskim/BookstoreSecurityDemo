package com.example.bookstore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@RequiredArgsConstructor
public class InsecureAuthenticationProvider implements AuthenticationProvider {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                String role = rs.getString("role");
                return new UsernamePasswordAuthenticationToken(
                        User.withUsername(email)
                                .password(password)
                                .roles(role)
                                .build(),
                        password,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                );
            } else {
                throw new BadCredentialsException("Invalid login");
            }
        });
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
