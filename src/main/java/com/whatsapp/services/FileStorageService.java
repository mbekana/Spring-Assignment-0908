package com.whatsapp.services;

import com.whatsapp.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import com.whatsapp.config.FileStorageProperties;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory to store files.", ex);
        }
    }

    public String storeFile(MultipartFile file, String subDirectory) {
        try {
            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Resolve the subdirectory within the root directory
            Path subDirectoryPath = this.fileStorageLocation.resolve(subDirectory);

            // Create the subdirectory if it doesn't exist
            Files.createDirectories(subDirectoryPath);

            // Generate a unique file name to avoid conflicts
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

            // Save the file to the subdirectory
            Path targetLocation = subDirectoryPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
        }
    }
}
