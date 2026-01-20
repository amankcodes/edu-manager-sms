package com.futureedu.manager.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student/attendance")
@CrossOrigin
public class AttendanceController {

    @GetMapping("/{id}")
    public Map<String, Object> getAttendance(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        response.put("studentId", id);
        response.put("totalClasses", 120);
        response.put("attendedClasses", 102);
        response.put("attendancePercentage", "85%");

        return response;
    }
}


