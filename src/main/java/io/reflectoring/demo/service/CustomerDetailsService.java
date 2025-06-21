package io.reflectoring.demo.service;

import io.reflectoring.demo.entity.User;
import io.reflectoring.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(STR."User not found with email: \{username}"));
    }

    public void registerUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new IllegalStateException("User already exists");
                });

        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    };

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}

