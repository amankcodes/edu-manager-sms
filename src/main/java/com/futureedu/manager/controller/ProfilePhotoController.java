package com.futureedu.manager.controller;

import com.futureedu.manager.model.User;
import com.futureedu.manager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/student/photo")
@CrossOrigin
public class ProfilePhotoController {

    private static final Logger log = LoggerFactory.getLogger(ProfilePhotoController.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final UserRepository userRepository;

    public ProfilePhotoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================== UPLOAD PHOTO ==================
    @PostMapping("/{id}")
    public ResponseEntity<?> uploadPhoto(
            @PathVariable Long id,
            @RequestParam("photo") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file selected");
            }

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // Sanitize original filename to prevent path traversal:
            // strip directory components, then whitelist safe characters
            String originalName = file.getOriginalFilename();
            String baseName = (originalName == null ? "upload" : new File(originalName).getName());
            // Remove leading dots (hidden files / relative traversal) and allow only alphanumerics, dash, underscore, dot
            String safeName = baseName.replaceAll("^[.]+", "").replaceAll("[^a-zA-Z0-9._-]", "_");
            if (safeName.isEmpty()) {
                safeName = "upload";
            }

            String fileName = id + "_" + System.currentTimeMillis() + "_" + safeName;

            File destination = new File(dir, fileName);
            file.transferTo(destination);

            user.setPhoto(fileName);
            userRepository.save(user);

            return ResponseEntity.ok("Photo uploaded successfully");

        } catch (Exception e) {
            log.error("Photo upload failed for student {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body("Upload failed: " + e.getMessage());
        }
    }

    // ================== VIEW PHOTO ==================
    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewPhoto(@PathVariable Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            if (user.getPhoto() == null) {
                return ResponseEntity.notFound().build();
            }

            File file = new File(uploadDir + File.separator + user.getPhoto());
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            Path path = file.toPath();
            byte[] imageBytes = Files.readAllBytes(path);

            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(imageBytes);

        } catch (Exception e) {
            log.error("Photo retrieval failed for student {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body("Error loading image");
        }
    }
}

