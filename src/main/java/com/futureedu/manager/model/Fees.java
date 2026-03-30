package com.futureedu.manager.model;

import jakarta.persistence.*;

@Entity
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private int totalAmount;
    private int paidAmount;

    public int getPendingAmount() {
        return totalAmount - paidAmount;
    }

    // getters
    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public int getTotalAmount() { return totalAmount; }
    public int getPaidAmount() { return paidAmount; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }
    public void setPaidAmount(int paidAmount) { this.paidAmount = paidAmount; }
}
