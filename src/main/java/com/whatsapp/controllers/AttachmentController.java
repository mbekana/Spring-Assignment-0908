package com.whatsapp.controllers;

import com.whatsapp.dtos.AttachmentDTO;
import com.whatsapp.entity.Attachment;
import com.whatsapp.mappers.AttachmentMapper;
import com.whatsapp.services.AttachmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final AttachmentMapper attachmentMapper;

    @PostMapping()
    public ResponseEntity<AttachmentDTO> uploadAttachment(
            @RequestParam Long chatroomId,
            @RequestParam String caption,
            @RequestParam("file") MultipartFile file
    ) {
        AttachmentDTO attachment = attachmentMapper.toAttachmentDTO(attachmentService.uploadAttachment(file, chatroomId, caption));
        return new ResponseEntity<>(attachment, HttpStatus.OK);
    }
}