package com.futureedu.manager.controller;

import com.futureedu.manager.model.Grade;
import com.futureedu.manager.repository.GradeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/grades")
@CrossOrigin
public class GradeController {

    private final GradeRepository gradeRepository;

    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/{studentId}")
    public List<Grade> getGrades(@PathVariable Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) {
        Grade saved = gradeRepository.save(grade);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(
            @PathVariable Long id,
            @RequestBody Grade updated) {

        return gradeRepository.findById(id)
                .map(record -> {
                    record.setSubject(updated.getSubject());
                    record.setExamType(updated.getExamType());
                    record.setMarksObtained(updated.getMarksObtained());
                    record.setTotalMarks(updated.getTotalMarks());
                    return ResponseEntity.ok(gradeRepository.save(record));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        if (!gradeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gradeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
