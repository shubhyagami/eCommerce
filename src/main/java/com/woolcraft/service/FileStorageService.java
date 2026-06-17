package com.woolcraft.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${woolcraft.upload-dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try { Files.createDirectories(Paths.get(uploadDir)); }
        catch (IOException e) { throw new RuntimeException("Could not create upload directory"); }
    }

    public String storeFile(MultipartFile file) {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try { Files.copy(file.getInputStream(), Paths.get(uploadDir, filename), StandardCopyOption.REPLACE_EXISTING); return filename; }
        catch (IOException e) { throw new RuntimeException("Failed to store file"); }
    }

    public void deleteFile(String filename) {
        try { Files.deleteIfExists(Paths.get(uploadDir, filename)); } catch (IOException e) {}
    }
}
