package com.futureedu.manager.controller;

import com.futureedu.manager.model.User;
import com.futureedu.manager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    // ================= GET PROFILE =================
    @GetMapping("/profile/{id}")
    public User getProfile(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ================= UPDATE PROFILE =================
    @PutMapping("/profile/{id}")
    public ResponseEntity<?> updateProfile(
            @PathVariable Long id,
            @RequestBody User updated) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updated.getName());
                    user.setEmail(updated.getEmail());
                    user.setPhone(updated.getPhone());
                    user.setCourse(updated.getCourse());
                    user.setYear(updated.getYear());
                    user.setAddress(updated.getAddress());

                    userRepository.save(user);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
