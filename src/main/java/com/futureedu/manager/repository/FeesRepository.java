package com.futureedu.manager.repository;

import com.futureedu.manager.model.Fees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeesRepository
        extends JpaRepository<Fees, Long> {

    Fees findByStudentId(Long studentId);
}
