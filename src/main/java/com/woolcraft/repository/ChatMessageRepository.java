package com.woolcraft.repository;

import com.woolcraft.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomIdOrderBySentAtAsc(Long chatRoomId);
    long countByChatRoomIdAndIsReadFalse(Long chatRoomId);
}
