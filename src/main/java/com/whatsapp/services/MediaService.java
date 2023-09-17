package com.whatsapp.services;

import com.whatsapp.entity.Media;
import com.whatsapp.enums.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MediaService {
    Media uploadMedia(MultipartFile file, Long chatroomId, String caption);
}
