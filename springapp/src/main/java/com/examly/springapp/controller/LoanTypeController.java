package com.examly.springapp.controller;

import com.examly.springapp.model.LoanType;
import com.examly.springapp.repository.LoanTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loantypes")
public class LoanTypeController {

    @Autowired
    private LoanTypeRepo loanTypeRepo;

    @PostMapping
    public ResponseEntity<LoanType> addLoanType(@RequestBody LoanType loanType) {
        LoanType savedLoanType = loanTypeRepo.save(loanType);
        return new ResponseEntity<>(savedLoanType, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanType>> getAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeRepo.findAll();
        return ResponseEntity.ok(loanTypes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanType> updateLoanType(@PathVariable Long id, @RequestBody LoanType loanType) {
        if (!loanTypeRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        loanType.setLoanTypeId(id);
        LoanType updatedLoanType = loanTypeRepo.save(loanType);
        return ResponseEntity.ok(updatedLoanType);
    }
}
