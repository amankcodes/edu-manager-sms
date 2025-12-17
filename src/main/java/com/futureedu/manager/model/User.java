package com.futureedu.manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;   // 🔐 IMPORTANT

    private String phone;
    private String course;
    private String year;
    private String address;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, FACULTY, STUDENT
    }

    // ===== GETTERS =====
    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() {   // ✅ ADDED
        return password;
    }

    public String getPhone() { return phone; }

    public String getCourse() { return course; }

    public String getYear() { return year; }

    public String getAddress() { return address; }

    public String getPhoto() { return photo; }

    public Role getRole() { return role; }

    // ===== SETTERS =====
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {   // ✅ FIXED
        this.email = email;
    }

    public void setPassword(String password) {   // ✅ ADDED
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
