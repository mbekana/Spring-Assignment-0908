package com.whatsapp.controllers;

import com.whatsapp.dtos.MediaDTO;
import com.whatsapp.entity.Media;
import com.whatsapp.mappers.MediaMapper;
import com.whatsapp.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @PostMapping("/upload")
    public ResponseEntity<MediaDTO> uploadMedia(
            @RequestParam("file") MultipartFile file,
            @RequestParam("chatroomId") Long chatroomId,
            @RequestParam("caption") String caption
    ) {
        Media media = mediaService.uploadMedia(file,  chatroomId, caption);

        return new ResponseEntity<>(mediaMapper.toMideaDTO(media), HttpStatus.OK);
    }
}