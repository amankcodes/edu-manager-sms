package com.futureedu.manager.controller;

import com.futureedu.manager.model.Attendance;
import com.futureedu.manager.model.Fees;
import com.futureedu.manager.repository.AttendanceRepository;
import com.futureedu.manager.repository.FeesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentDataController {

    private final AttendanceRepository attendanceRepo;
    private final FeesRepository feesRepo;

    public StudentDataController(AttendanceRepository attendanceRepo,
                                 FeesRepository feesRepo) {
        this.attendanceRepo = attendanceRepo;
        this.feesRepo = feesRepo;
    }

    // 📅 Attendance API
    @GetMapping("/{id}/attendance")
    public List<Attendance> getAttendance(@PathVariable Long id) {
        return attendanceRepo.findByStudentId(id);
    }

    // 💰 Fees API
    @GetMapping("/{id}/fees")
    public Fees getFees(@PathVariable Long id) {
        return feesRepo.findByStudentId(id);
    }
}
