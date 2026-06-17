package com.woolcraft.service;

import com.woolcraft.dto.RegisterRequest;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(RegisterRequest request) {
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow();
        User user = User.builder().name(request.getName()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).phone(request.getPhone())
                .enabled(true).createdAt(java.time.LocalDateTime.now())
                .roles(new HashSet<>(Set.of(userRole))).build();
        user = userRepository.save(user);
        cartRepository.save(Cart.builder().user(user).build());
        return user;
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email).orElse(null); }
    public User findById(Long id) { return userRepository.findById(id).orElse(null); }

    @Transactional
    public User updateProfile(Long userId, String name, String phone, String address, String city, String state, String postalCode) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setName(name); user.setPhone(phone); user.setAddress(address);
        user.setCity(city); user.setState(state); user.setPostalCode(postalCode);
        return userRepository.save(user);
    }

    public List<User> findAllCustomers() {
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
        if (userRole == null) return List.of();
        return userRepository.findAll().stream().filter(u -> u.getRoles().contains(userRole)).toList();
    }

    public long countCustomers() { return findAllCustomers().size(); }

    @Transactional
    public void toggleEnabled(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }
}
