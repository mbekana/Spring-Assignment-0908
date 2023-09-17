package com.whatsapp.dtos;


import com.whatsapp.enums.MessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Long id;
    private MessageType type;
    private String content;
    private String attachmentPath;
    private LocalDateTime sentAt;
    private boolean isRead;
    private Long chatroomId;
    private Long senderId;
}