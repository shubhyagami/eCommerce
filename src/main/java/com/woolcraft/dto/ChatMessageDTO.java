package com.woolcraft.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private Long roomId; private String sender, content, sentAt;
}
