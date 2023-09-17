package com.whatsapp.services;

import com.whatsapp.repositories.MediaRepository;
import com.whatsapp.entity.Media;
import com.whatsapp.enums.MediaType;
import lombok.RequiredArgsConstructor;
import com.whatsapp.exceptions.InvalidMediaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{
    private final MediaRepository mediaRepository;
    private final FileStorageService fileStorageService;
    private final ChatroomService chatroomService;

    @Override
    public Media uploadMedia(MultipartFile file, Long chatroomId, String caption) {
        String fileType;

        // Determine the media type based on the file's content type
        if (file.getContentType().startsWith("image")) {
            fileType = "picture";
        } else if (file.getContentType().startsWith("video")) {
            fileType = "video";
        } else {
            throw new InvalidMediaException("Invalid media type.");
        }

        // Check the file size only if it's a video
        if ("video".equals(fileType) && file.getSize() < 10 * 1024 * 1024) {
            throw new InvalidMediaException("Minimum video size is 10MB.");
        }

        // Perform the file storage and database operations as before
        String filePath = fileStorageService.storeFile(file, fileType);

        // Create a Media object to save in the database
        Media media = new Media();
        media.setMediaType(fileType.equals("picture") ? MediaType.PICTURE : MediaType.VIDEO);
        media.setCaption(caption);
        media.setUploadedAt(LocalDateTime.now());
        media.setDuration(0L);
        media.setChatroom(chatroomService.getChatroomById(chatroomId));

        // Save the media information in the database
        mediaRepository.save(media);

        // Return the Media object
        return media;
    }

}
