package com.examly.springapp.repository;

import com.examly.springapp.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByEmail(String email);
    
    @Query("SELECT c FROM Customer c WHERE c.creditScore >= ?1")
    List<Customer> findByCreditScoreGreaterThanEqual(Double creditScore);
    
    Page<Customer> findAll(Pageable pageable);
}