package com.examly.springapp.repository;

import com.examly.springapp.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypeRepo extends JpaRepository<LoanType, Long> {
}