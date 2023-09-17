package com.whatsapp.controllers;

import com.whatsapp.dtos.MessageDTO;
import com.whatsapp.entity.Chatroom;
import com.whatsapp.entity.Message;
import com.whatsapp.entity.User;
import com.whatsapp.enums.MessageType;
import com.whatsapp.exceptions.ChatroomNotFoundException;
import com.whatsapp.exceptions.UserNotFoundException;
import com.whatsapp.mappers.MessageMapper;
import com.whatsapp.services.ChatroomService;
import com.whatsapp.services.MessageService;
import com.whatsapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ChatroomService chatroomService;
    private final MessageMapper messageMapper;

    @PostMapping("/text")
    public ResponseEntity<Message> sendTextMessage(@RequestBody MessageDTO messageDTO) {
        try {
            Long chatroomId = messageDTO.getChatroomId();
            Long userId = messageDTO.getSenderId(); // Corrected to getSenderId()
            String text = messageDTO.getContent();

            Chatroom chatroom = chatroomService.getChatroomById(chatroomId);
            User user = userService.getUserById(userId);

            Message message = messageMapper.toMessage(messageDTO);
            message.setMessageType(MessageType.TEXT);
            message.setSentAt(LocalDateTime.now());
            message.setChatroom(chatroom);
            message.setSender(user);

            Message savedMessage = messageService.sendMessage(message);

            return new ResponseEntity<>(savedMessage, HttpStatus.OK);
        } catch (ChatroomNotFoundException | UserNotFoundException e) {
            // Handle exceptions here (you can customize this part)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/chatroom/{chatroomId}")
    public ResponseEntity<List<Message>> getMessagesForChatroom(@PathVariable Long chatroomId) {
        try {
            List<Message> messages = messageService.getMessagesForChatroom(chatroomId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (ChatroomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
