package com.futureedu.manager.controller;

import com.futureedu.manager.model.User;
import com.futureedu.manager.repository.UserRepository;
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

            String fileName = id + "_" + System.currentTimeMillis()
                    + "_" + file.getOriginalFilename();

            File destination = new File(dir, fileName);
            file.transferTo(destination);

            user.setPhoto(fileName);
            userRepository.save(user);

            return ResponseEntity.ok("Photo uploaded successfully");

        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error loading image");
        }
    }
}
