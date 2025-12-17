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

    public int getPercentage() {
        return (presentClasses * 100) / totalClasses;
    }

    // getters & setters
}
