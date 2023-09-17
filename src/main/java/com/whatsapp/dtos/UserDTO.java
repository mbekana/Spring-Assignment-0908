package com.whatsapp.dtos;

import com.whatsapp.entity.Chatroom;
import lombok.Data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String phoneNumber;
    private String profilePictureUrl;
    private String status;
    private boolean online;
    private Set<Chatroom> chatrooms = new HashSet<>();

}
