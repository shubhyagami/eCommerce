package com.woolcraft.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ContactMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String email;
    private String phone;
    @Column(columnDefinition = "TEXT", nullable = false) private String message;
    @Column(columnDefinition = "TEXT") private String reply;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
}
