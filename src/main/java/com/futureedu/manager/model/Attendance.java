package com.futureedu.manager.model;

import jakarta.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String subject;
    private int totalClasses;
    private int presentClasses;

    public int getAbsentClasses() {
        return totalClasses - presentClasses;
    }

    public double getPercentage() {
        if (totalClasses == 0) return 0.0;
        return (presentClasses * 100.0) / totalClasses;
    }

    // getters
    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public String getSubject() { return subject; }
    public int getTotalClasses() { return totalClasses; }
    public int getPresentClasses() { return presentClasses; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setTotalClasses(int totalClasses) { this.totalClasses = totalClasses; }
    public void setPresentClasses(int presentClasses) { this.presentClasses = presentClasses; }
}
