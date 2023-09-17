package com.whatsapp.services;

import com.whatsapp.dtos.MessageDTO;
import com.whatsapp.entity.Message;
import com.whatsapp.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;
    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesForChatroom(Long chatroomId) {
        return messageRepository.findByChatroom_IdOrderBySentAtAsc(chatroomId);
    }
}
