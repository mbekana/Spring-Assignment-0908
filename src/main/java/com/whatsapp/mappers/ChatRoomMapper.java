package com.whatsapp.mappers;

import com.whatsapp.dtos.ChatroomDTO;
import com.whatsapp.entity.Chatroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {
    Chatroom toChatroom(ChatroomDTO chatroomDTO);
    ChatroomDTO toChatroomDTO(Chatroom chatroom);
}
