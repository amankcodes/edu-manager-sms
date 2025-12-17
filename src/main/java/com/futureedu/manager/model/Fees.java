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

    // getters & setters
}
