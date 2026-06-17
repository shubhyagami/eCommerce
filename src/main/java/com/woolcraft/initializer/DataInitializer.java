package com.woolcraft.initializer;

import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (roleRepository.count() > 0) return;

        Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
        Role userRole = roleRepository.save(new Role("ROLE_USER"));

        userRepository.save(User.builder().name("Admin").email("admin@woolcraft.com")
                .password(passwordEncoder.encode("admin123")).enabled(true)
                .createdAt(java.time.LocalDateTime.now())
                .roles(new HashSet<>(Set.of(adminRole, userRole))).build());

        String[][] cats = {{"Floral Clips","floral-clips"},{"Bow Clips","bow-clips"},
            {"Kids Hair Clips","kids-hair-clips"},{"Handmade Collection","handmade-collection"},
            {"Premium Collection","premium-collection"}};
        for (String[] c : cats)
            categoryRepository.save(Category.builder().name(c[0]).slug(c[1]).active(true).build());
    }
}
