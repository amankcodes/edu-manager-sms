package com.futureedu.manager.model;

import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String subject;
    private String examType;   // e.g. Midterm, Final, Assignment
    private double marksObtained;
    private double totalMarks;

    public double getPercentage() {
        if (totalMarks == 0) return 0.0;
        return (marksObtained * 100.0) / totalMarks;
    }

    public String getGradeLetter() {
        double pct = getPercentage();
        if (pct >= 90) return "A+";
        if (pct >= 80) return "A";
        if (pct >= 70) return "B";
        if (pct >= 60) return "C";
        if (pct >= 50) return "D";
        return "F";
    }

    // getters
    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public String getSubject() { return subject; }
    public String getExamType() { return examType; }
    public double getMarksObtained() { return marksObtained; }
    public double getTotalMarks() { return totalMarks; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setExamType(String examType) { this.examType = examType; }
    public void setMarksObtained(double marksObtained) { this.marksObtained = marksObtained; }
    public void setTotalMarks(double totalMarks) { this.totalMarks = totalMarks; }
}
