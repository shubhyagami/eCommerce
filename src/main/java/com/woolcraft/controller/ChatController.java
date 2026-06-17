package com.woolcraft.controller;

import com.woolcraft.dto.ChatMessageDTO;
import com.woolcraft.entity.*;
import com.woolcraft.service.ChatService;
import com.woolcraft.repository.UserRepository;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;

    @GetMapping("/chat")
    public String chat(Model model, Principal p) {
        if (p == null) return "redirect:/login";
        User user = userRepository.findByEmail(p.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        ChatRoom room = chatService.getOrCreateRoom(user.getId());
        model.addAttribute("room", room);
        model.addAttribute("messages", chatService.getMessages(room.getId()));
        return "chat";
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessageDTO sendMessage(ChatMessageDTO msg) {
        chatService.saveMessage(msg.getRoomId(), msg.getSender(), msg.getContent());
        return msg;
    }

    @GetMapping("/chat/history/{roomId}")
    @ResponseBody
    public List<ChatMessageDTO> getHistory(@PathVariable Long roomId) {
        return chatService.getMessages(roomId);
    }
}
