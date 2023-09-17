package com.whatsapp.services;

import com.whatsapp.dtos.MessageDTO;
import com.whatsapp.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    Message sendMessage(Message message);
    List<Message> getMessagesForChatroom(Long chatroomId);

}
