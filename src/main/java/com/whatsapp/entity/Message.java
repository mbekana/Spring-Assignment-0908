package com.whatsapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whatsapp.config.Auditable;
import com.whatsapp.enums.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name="Message")
@Table(name="messages")
@Data
public class Message extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MessageType messageType = MessageType.TEXT;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    @JsonIgnore
    private Chatroom chatroom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    private User sender;

}
