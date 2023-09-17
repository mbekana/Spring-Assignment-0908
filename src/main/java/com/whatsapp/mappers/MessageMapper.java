package com.whatsapp.mappers;

import com.whatsapp.dtos.AttachmentDTO;
import com.whatsapp.dtos.MessageDTO;
import com.whatsapp.entity.Attachment;
import com.whatsapp.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toMessage(MessageDTO messageDTO);
    MessageDTO toMessageDTO(Message message);
}
