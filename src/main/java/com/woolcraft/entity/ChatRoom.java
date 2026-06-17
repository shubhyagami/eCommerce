package com.woolcraft.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat_rooms")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") private User user;
    private String adminName;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL) private List<ChatMessage> messages;
}
