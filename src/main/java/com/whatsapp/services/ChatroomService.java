package com.whatsapp.services;

import com.whatsapp.entity.Chatroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatroomService {
    Chatroom leaveChatroom(Long chatroomId, Long userId);

    Chatroom createChatRoom(Chatroom chatRoom);
    Chatroom joinChatroom(Long chatroomId, Long userId);

    Chatroom getChatroomById(Long chatRoomId);

}
