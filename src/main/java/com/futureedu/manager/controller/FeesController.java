package com.futureedu.manager.controller;

import com.futureedu.manager.model.Fees;
import com.futureedu.manager.repository.FeesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/fees")
@CrossOrigin
public class FeesController {

    private final FeesRepository feesRepository;

    public FeesController(FeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fees> getFees(@PathVariable Long id) {
        Fees fees = feesRepository.findByStudentId(id);
        if (fees == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fees);
    }

    @PostMapping
    public ResponseEntity<Fees> addFees(@RequestBody Fees fees) {
        Fees saved = feesRepository.save(fees);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fees> updateFees(
            @PathVariable Long id,
            @RequestBody Fees updated) {

        return feesRepository.findById(id)
                .map(record -> {
                    record.setTotalAmount(updated.getTotalAmount());
                    record.setPaidAmount(updated.getPaidAmount());
                    // studentId is intentionally not updated — records are immutable to their owner
                    return ResponseEntity.ok(feesRepository.save(record));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFees(@PathVariable Long id) {
        if (!feesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        feesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
