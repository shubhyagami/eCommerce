package com.woolcraft.service;

import com.woolcraft.dto.ChatMessageDTO;
import com.woolcraft.entity.*;
import com.woolcraft.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public ChatRoom getOrCreateRoom(Long userId) {
        return chatRoomRepository.findByUserId(userId).orElseGet(() ->
                chatRoomRepository.save(ChatRoom.builder().user(userRepository.findById(userId).orElseThrow()).createdAt(LocalDateTime.now()).build()));
    }

    public List<ChatMessageDTO> getMessages(Long roomId) {
        return chatMessageRepository.findByChatRoomIdOrderBySentAtAsc(roomId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ChatRoom> getAllRooms() { return chatRoomRepository.findAllByOrderByCreatedAtDesc(); }
    public ChatRoom getRoom(Long roomId) { return chatRoomRepository.findById(roomId).orElse(null); }

    @Transactional
    public ChatMessage saveMessage(Long roomId, String sender, String content) {
        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow();
        return chatMessageRepository.save(ChatMessage.builder().chatRoom(room).sender(sender).content(content).sentAt(LocalDateTime.now()).isRead(false).build());
    }

    public long getUnreadCount(Long roomId) { return chatMessageRepository.countByChatRoomIdAndIsReadFalse(roomId); }

    @Transactional
    public void markAsRead(Long roomId) {
        chatMessageRepository.findByChatRoomIdOrderBySentAtAsc(roomId).forEach(m -> { if (!m.isRead()) { m.setRead(true); chatMessageRepository.save(m); } });
    }

    private ChatMessageDTO toDTO(ChatMessage m) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setRoomId(m.getChatRoom().getId()); dto.setSender(m.getSender());
        dto.setContent(m.getContent()); dto.setSentAt(m.getSentAt().toString());
        return dto;
    }
}
