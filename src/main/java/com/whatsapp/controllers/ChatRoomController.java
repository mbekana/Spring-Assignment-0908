package com.whatsapp.controllers;

import com.whatsapp.dtos.ChatRoomRequestDTO;
import com.whatsapp.dtos.ChatroomDTO;
import com.whatsapp.entity.Chatroom;
import com.whatsapp.exceptions.UserAlreadyInChatroomException;
import com.whatsapp.mappers.ChatRoomMapper;
import com.whatsapp.services.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatroomService chatroomService;
    private final ChatRoomMapper chatRoomMapper;

    @PostMapping("/createChatRoom")
    public ResponseEntity<ChatroomDTO> createChatRoom(@RequestBody ChatroomDTO chatroomDTO) {
        ChatroomDTO createdChatroom = chatRoomMapper.toChatroomDTO(chatroomService.createChatRoom(chatRoomMapper.toChatroom(chatroomDTO)));
        return new ResponseEntity<>(createdChatroom, HttpStatus.CREATED);
    }

    @PostMapping("/leave")
    public ResponseEntity<ChatroomDTO> leaveChatRoom(@RequestBody ChatRoomRequestDTO request) {
        ChatroomDTO leftChatroom = chatRoomMapper.toChatroomDTO(chatroomService.leaveChatroom(request.getChatroomId(), request.getUserId()));
        return new ResponseEntity<>(leftChatroom, HttpStatus.OK);
    }

    @PutMapping("/joinChatRoom")
    public ResponseEntity<ChatroomDTO> joinChatRoom(@RequestBody ChatRoomRequestDTO request) {
        Long chatroomId = request.getChatroomId();
        Long userId = request.getUserId(); // Implement a method to get the current user's ID

        // Call the service method to join the chatroom
        ChatroomDTO joinedChatroom = chatRoomMapper.toChatroomDTO(chatroomService.joinChatroom(chatroomId, userId));

        return new ResponseEntity<>(joinedChatroom, HttpStatus.OK);
    }


    @GetMapping("/chat-rooms/{chatRoomId}")
    public ResponseEntity<ChatroomDTO> getChatRoomById(@PathVariable Long chatRoomId) {
        // Implement logic to get a chat room by its ID
        ChatroomDTO chatRoom = chatRoomMapper.toChatroomDTO(chatroomService.getChatroomById(chatRoomId));
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

}
