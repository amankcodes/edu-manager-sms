package com.futureedu.manager.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student/fees")
@CrossOrigin
public class FeesController {

    @GetMapping("/{id}")
    public Map<String, Object> getFees(@PathVariable Long id) {

        Map<String, Object> fees = new HashMap<>();
        fees.put("studentId", id);
        fees.put("totalFees", 50000);
        fees.put("paidFees", 12000);
        fees.put("pendingFees", 38000);

        return fees;
    }
}
