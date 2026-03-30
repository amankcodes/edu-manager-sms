package com.futureedu.manager.controller;

import com.futureedu.manager.model.User;
import com.futureedu.manager.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

    private final UserRepository userRepository;

    public StudentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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

