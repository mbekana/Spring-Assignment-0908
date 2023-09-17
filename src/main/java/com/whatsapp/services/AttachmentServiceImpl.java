package com.whatsapp.services;

import com.whatsapp.entity.Attachment;
import com.whatsapp.entity.Chatroom;
import com.whatsapp.exceptions.ChatroomNotFoundException;
import com.whatsapp.repositories.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final FileStorageService fileStorageService;
    private final ChatroomService chatroomService;

    @Override
    public Attachment uploadAttachment(MultipartFile file, Long chatroomId, String caption) {
        String fileType = "attachment";

        // Check if the chatroom with the provided chatroomId exists
        Chatroom chatroom = chatroomService.getChatroomById(chatroomId);
        if (chatroom == null) {
            throw new ChatroomNotFoundException("Chatroom not found with ID: " + chatroomId);
        }

        // Perform the file storage and database operations
        String filePath = fileStorageService.storeFile(file, fileType);

        // Create an Attachment object to save in the database
        Attachment attachment = new Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFilePath(filePath);
        attachment.setMimeType(file.getContentType());
        attachment.setFileSize(file.getSize());
        attachment.setCaption(caption); // Set the caption as needed
//        attachment.setUploadedAt(LocalDateTime.now());
        attachment.setChatroom(chatroom);

        // Save the attachment information in the database
        attachmentRepository.save(attachment);

        // Return the Attachment object
        return attachment;
    }
}
