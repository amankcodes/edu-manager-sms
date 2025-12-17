package com.futureedu.manager.controller;

import com.futureedu.manager.model.User;
import com.futureedu.manager.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> data) {

        String email = data.get("email");
        String password = data.get("password");

        Map<String, Object> response = new HashMap<>();

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            response.put("status", "FAILED");
            response.put("message", "Invalid credentials");
            return response;
        }

        response.put("status", "SUCCESS");
        response.put("name", user.getName());
        response.put("role", user.getRole());
        response.put("userId", user.getId());

        return response;
    }
}

