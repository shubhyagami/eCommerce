package com.woolcraft.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chat_room_id") private ChatRoom chatRoom;
    @Column(nullable = false) private String sender;
    @Column(columnDefinition = "TEXT", nullable = false) private String content;
    @Column(name = "sent_at") private LocalDateTime sentAt = LocalDateTime.now();
    private boolean isRead;
}
