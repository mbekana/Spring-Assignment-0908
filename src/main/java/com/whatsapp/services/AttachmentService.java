package com.whatsapp.services;

import com.whatsapp.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment uploadAttachment(MultipartFile file, Long chatroomId, String caption);
}