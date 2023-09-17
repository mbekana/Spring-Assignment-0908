package com.whatsapp.entity;

import com.whatsapp.config.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name="chatrooms")
public class Chatroom extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String profilePictureUrl;
    private boolean isGroupChat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "chatroom_member",
            joinColumns = @JoinColumn(name = "chatroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "chatroom")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "chatroom")
    private List<Media> media = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chatroom chatroom = (Chatroom) o;
        return Objects.equals(id, chatroom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
