package com.futureedu.manager.controller;

import com.futureedu.manager.model.Attendance;
import com.futureedu.manager.repository.AttendanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/attendance")
@CrossOrigin
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    public AttendanceController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping("/{id}")
    public List<Attendance> getAttendance(@PathVariable Long id) {
        return attendanceRepository.findByStudentId(id);
    }

    @PostMapping
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        Attendance saved = attendanceRepository.save(attendance);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance updated) {

        return attendanceRepository.findById(id)
                .map(record -> {
                    record.setSubject(updated.getSubject());
                    record.setTotalClasses(updated.getTotalClasses());
                    record.setPresentClasses(updated.getPresentClasses());
                    return ResponseEntity.ok(attendanceRepository.save(record));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        if (!attendanceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        attendanceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


