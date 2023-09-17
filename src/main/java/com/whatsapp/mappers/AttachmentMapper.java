package com.whatsapp.mappers;

import com.whatsapp.dtos.AttachmentDTO;
import com.whatsapp.dtos.ChatroomDTO;
import com.whatsapp.entity.Attachment;
import com.whatsapp.entity.Chatroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    Attachment toAttachment(AttachmentDTO attachmentDTO);
    AttachmentDTO toAttachmentDTO(Attachment attachment);
}
