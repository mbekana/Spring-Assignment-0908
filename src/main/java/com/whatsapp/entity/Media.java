package com.whatsapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whatsapp.config.Auditable;
import com.whatsapp.enums.MediaType;
import com.whatsapp.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "Media")
@Data
@Table(name="medias")
public class Media extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private LocalDateTime uploadedAt;
    private Long duration;
    private MediaType mediaType;

    private MessageType messageType = MessageType.MEDIA;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    @JsonIgnore
    private Chatroom chatroom;
}
