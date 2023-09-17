package com.whatsapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whatsapp.config.Auditable;
import lombok.Data;

@Data
public class AttachmentDTO extends Auditable {
    private Long id;
    private String fileName;
    private String filePath;
    private String mimeType;
    private Long fileSize;
    @JsonIgnore
    private Long chatroomId;
}
