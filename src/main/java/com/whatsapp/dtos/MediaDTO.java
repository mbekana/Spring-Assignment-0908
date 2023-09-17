package com.whatsapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whatsapp.config.Auditable;
import com.whatsapp.entity.Chatroom;
import com.whatsapp.enums.MediaType;
import com.whatsapp.enums.MessageType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MediaDTO extends Auditable {
    private Long id;
    private String caption;
    private LocalDateTime uploadedAt;
    private Long duration;
    private MediaType mediaType;

    private MessageType messageType = MessageType.MEDIA;

    @JsonIgnore
    private Chatroom chatroom;
}
