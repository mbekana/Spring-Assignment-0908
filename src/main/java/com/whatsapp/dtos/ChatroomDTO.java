package com.whatsapp.dtos;

import com.whatsapp.config.Auditable;
import com.whatsapp.entity.Media;
import com.whatsapp.entity.Message;
import com.whatsapp.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ChatroomDTO extends Auditable {
    private Long id;
    private String name;
    private String description;
    private String profilePictureUrl;
    private boolean isGroupChat;

    private Set<User> members ;

    private List<Message> messages ;

    private List<Media> media;
}