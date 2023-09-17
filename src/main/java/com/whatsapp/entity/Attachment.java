package com.whatsapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whatsapp.config.Auditable;
import com.whatsapp.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name="Attachment")
@Data
@Table(name="attachments")
public class Attachment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    private String mimeType;
    private Long fileSize;
    private String caption;
    private MessageType messageType = MessageType.ATTACHEMENT;


    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    @JsonIgnore
    private Chatroom chatroom;
}
